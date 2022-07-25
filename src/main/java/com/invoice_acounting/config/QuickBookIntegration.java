package com.invoice_acounting.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intuit.ipp.core.Context;
import com.intuit.ipp.core.ServiceType;
import com.intuit.ipp.security.IAuthorizer;
import com.intuit.ipp.security.OAuth2Authorizer;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.Config;
import com.intuit.oauth2.client.OAuth2PlatformClient;
import com.intuit.oauth2.config.Environment;
import com.intuit.oauth2.config.OAuth2Config;
import com.intuit.oauth2.data.BearerTokenResponse;
import com.invoice_acounting.modal.ConnectionModal;
import com.invoice_acounting.service.ConnectionService;
import com.invoice_acounting.util.UtilContants;

public class QuickBookIntegration {
//  @Value("${OAuth2AppClientId}")
	private String clientId = UtilContants.clientId;

	// @Value("${OAuth2AppClientSecret}")
	private String clientSecret = UtilContants.clientSecret;

	// @Value("${refreshToken}")
	private String refreshToken = UtilContants.refreshToken;

	// @Value("${RealmID}")

	private String RealmID = UtilContants.RealmID;

	@Autowired
	ConnectionService connectionService;

	public DataService onlineIntegartion() throws Exception {

		Environment quickBooksEnvironment = null;
		String quickBooksConnectURL = null;

		quickBooksEnvironment = Environment.SANDBOX;
		quickBooksConnectURL = "https://sandbox-quickbooks.api.intuit.com/v3/company";

		OAuth2Config oauth2Config = new OAuth2Config.OAuth2ConfigBuilder(clientId, clientSecret)
				.callDiscoveryAPI(quickBooksEnvironment).buildConfig();

		OAuth2PlatformClient client = new OAuth2PlatformClient(oauth2Config);
		// Get the bearer token (OAuth2 tokens)
		BearerTokenResponse bearerTokenResponse = null;
		int count = 0;
		int maxTries = 3;
		boolean isRetry = true;

		try {
			bearerTokenResponse = client.refreshToken(refreshToken);

			isRetry = false;
		} catch (Exception e) {
			throw e;
		}
		System.out.println(bearerTokenResponse.getAccessToken());

		ConnectionModal connection = new ConnectionModal();
		connection.setId(1L);
		connection.setAccessToken(bearerTokenResponse.getAccessToken());
		connection.setRealmeId(RealmID);
		connection.setRefreshToken(refreshToken);
		connectionService.save(connection);
		IAuthorizer oauth = new OAuth2Authorizer(bearerTokenResponse.getAccessToken());
		Config.setProperty(Config.BASE_URL_QBO, quickBooksConnectURL);
		Context context = new Context(oauth, ServiceType.QBO, RealmID);
		return new DataService(context);

	}
}
