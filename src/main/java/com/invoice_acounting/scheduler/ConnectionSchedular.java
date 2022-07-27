package com.invoice_acounting.scheduler;

import java.util.Date;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.intuit.ipp.core.Context;
import com.intuit.ipp.core.ServiceType;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.security.IAuthorizer;
import com.intuit.ipp.security.OAuth2Authorizer;
import com.intuit.ipp.util.Config;
import com.intuit.oauth2.client.OAuth2PlatformClient;
import com.intuit.oauth2.config.OAuth2Config;
import com.intuit.oauth2.data.BearerTokenResponse;
import com.intuit.oauth2.exception.OAuthException;
import com.invoice_acounting.modal.ConnectionModal;
import com.invoice_acounting.service.ConnectionService;
import com.invoice_acounting.util.UtilContants;

import lombok.extern.slf4j.Slf4j;


@EnableScheduling
@Configuration
@Slf4j
public class ConnectionSchedular {
	
	private ConnectionService connectionService;

    @Autowired // inject FirstServiceImpl
    public void ConnectionService(@Qualifier("connectionImplementation") ConnectionService connectionService) {
    	log.info("autowiring connection service", connectionService);
        this.connectionService = connectionService;
    }

    @Scheduled(cron = "0 * * ? * *")
    public void connectionStablished() throws OAuthException, FMSException {
        System.out.println("Connection" + new Date());
        log.info("connected in Scheduled");
        ConnectionModal connectionModel = connectionService.get(1L);
        System.out.println(connectionModel);
        if (ObjectUtils.isEmpty(connectionModel)) {
        	connectionModel=new ConnectionModal();
            connectionModel.setId(1L);
            connectionModel.setRealmId(UtilContants.RealmID);
            connectionModel.setRefreshToken(UtilContants.refreshToken);
            connectionModel.setQuickBooksConnectURL(UtilContants.quickBooksConnectURL);
            connectionModel.setClientId(UtilContants.clientId);
            connectionModel.setClientSecret(UtilContants.clientSecret);
        }
       

        OAuth2Config oauth2Config = new OAuth2Config.OAuth2ConfigBuilder(connectionModel.getClientId(), connectionModel.getClientSecret())
                .callDiscoveryAPI(UtilContants.quickBooksEnvironment).buildConfig();

        OAuth2PlatformClient client = new OAuth2PlatformClient(oauth2Config);

        // Get the bearer token (OAuth2 tokens)
        BearerTokenResponse bearerTokenResponse = null;
        int count = 0;
        int maxTries = 3;
        boolean isRetry = true;

        while (isRetry) {
            try {
            	log.info("refersh token",connectionModel.getRefreshToken());
                System.out.println(connectionModel.getRefreshToken());
                bearerTokenResponse = client.refreshToken(connectionModel.getRefreshToken());
                isRetry = false;
                System.out.println(bearerTokenResponse.getAccessToken());
                connectionModel.setAccessToken(bearerTokenResponse.getAccessToken());
                connectionService.save(connectionModel);
            } catch (Exception e) {
                throw e;
            }
        }

        if (!connectionModel.getRefreshToken().equals(bearerTokenResponse.getRefreshToken())) {
            connectionModel.setRefreshToken(bearerTokenResponse.getRefreshToken());
            try {
                connectionService.updateConnectionInfo(connectionModel);
                log.info("after update");
                System.out.println("After update " + connectionModel);
            } catch (Exception e) {
                throw e;
            }
        }

        IAuthorizer oauth = new OAuth2Authorizer(bearerTokenResponse.getAccessToken());
        Config.setProperty(Config.BASE_URL_QBO, UtilContants.quickBooksConnectURL);
        Context context = new Context(oauth, ServiceType.QBO, UtilContants.RealmID);
    }



}
