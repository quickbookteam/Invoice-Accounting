package com.accounting.scheduler;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.accounting.exception.CustomException;
import com.accounting.modal.ConnectionModal;
import com.accounting.service.ConnectionService;
import com.accounting.util.UtilConstants;
import com.intuit.ipp.exception.FMSException;
import com.intuit.oauth2.client.OAuth2PlatformClient;
import com.intuit.oauth2.config.OAuth2Config;
import com.intuit.oauth2.data.BearerTokenResponse;
import com.intuit.oauth2.exception.OAuthException;

import lombok.extern.slf4j.Slf4j;


@EnableScheduling
@Configuration
@Slf4j
public class ConnectionSchedular {
	
	private ConnectionService connectionService;

    @Autowired
    public  ConnectionSchedular( ConnectionService connectionService) {
    	log.info("autowiring connection service", connectionService);
        this.connectionService = connectionService;
    }

//   @Scheduled(cron = "0 * * ? * *")
    public void connectionStablished() throws OAuthException, FMSException {
      
        log.info("connected in Scheduled");
        
        ConnectionModal connectionModel = connectionService.get(1L);
      
        if (ObjectUtils.isEmpty(connectionModel)) {
        	connectionModel=new ConnectionModal();
            connectionModel.setId(1L);
            connectionModel.setRealmId(UtilConstants.RealmID);
            connectionModel.setRefreshToken(UtilConstants.refreshToken);
            connectionModel.setQuickBooksConnectURL(UtilConstants.quickBooksConnectURL);
            connectionModel.setClientId(UtilConstants.clientId);
            connectionModel.setClientSecret(UtilConstants.clientSecret);
        }
       

        OAuth2Config oauth2Config = new OAuth2Config.OAuth2ConfigBuilder(connectionModel.getClientId(), connectionModel.getClientSecret())
                .callDiscoveryAPI(UtilConstants.quickBooksEnvironment).buildConfig();

        OAuth2PlatformClient client = new OAuth2PlatformClient(oauth2Config);

        // Get the bearer token (OAuth2 tokens)
        BearerTokenResponse bearerTokenResponse = null;
        int count = 0;
        int maxTries = 3;
        boolean isRetry = true;

        while (isRetry) {
            try {
                           
                bearerTokenResponse = client.refreshToken(connectionModel.getRefreshToken());
                isRetry = false;
                log.info("refersh token",connectionModel.getRefreshToken());
                connectionModel.setAccessToken(bearerTokenResponse.getAccessToken());
                connectionService.save(connectionModel);
            } catch (Exception e) {
            	log.info(e.getMessage());
            	throw new CustomException(e.getMessage());
            }
        }

        if (!connectionModel.getRefreshToken().equals(bearerTokenResponse.getRefreshToken())) {
            connectionModel.setRefreshToken(bearerTokenResponse.getRefreshToken());
            try {
                connectionService.updateConnectionInfo(connectionModel);
                log.info("after update");
            } catch (Exception e) {
            	log.info(e.getMessage());
            	throw new CustomException(e.getMessage());
            }
        }

    }



}
