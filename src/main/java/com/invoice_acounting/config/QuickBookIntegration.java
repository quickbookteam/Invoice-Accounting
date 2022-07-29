package com.invoice_acounting.config;

import org.springframework.beans.factory.annotation.Autowired;

import com.invoice_acounting.service.ConnectionService;
import com.invoice_acounting.util.UtilConstants;

public class QuickBookIntegration {
//  @Value("${OAuth2AppClientId}")
	private String clientId = UtilConstants.clientId;

	// @Value("${OAuth2AppClientSecret}")
	private String clientSecret = UtilConstants.clientSecret;

	// @Value("${refreshToken}")
	private String refreshToken = UtilConstants.refreshToken;

	// @Value("${RealmID}")

	private String RealmID = UtilConstants.RealmID;

	@Autowired
	ConnectionService connectionService;
//
//	public DataService onlineIntegartion() throws Exception {
//
//		Environment quickBooksEnvironment = null;
//		String quickBooksConnectURL = null;
//
//		quickBooksEnvironment = Environment.SANDBOX;
//		quickBooksConnectURL = "https://sandbox-quickbooks.api.intuit.com/v3/company";
//
//		OAuth2Config oauth2Config = new OAuth2Config.OAuth2ConfigBuilder(clientId, clientSecret)
//				.callDiscoveryAPI(quickBooksEnvironment).buildConfig();
//
//		OAuth2PlatformClient client = new OAuth2PlatformClient(oauth2Config);
//		// Get the bearer token (OAuth2 tokens)
//		BearerTokenResponse bearerTokenResponse = null;
//		int count = 0;
//		int maxTries = 3;
//		boolean isRetry = true;
//
//		try {
//			bearerTokenResponse = client.refreshToken(refreshToken);
//
//			isRetry = false;
//		} catch (Exception e) {
//			throw e;
//		}
//		System.out.println(bearerTokenResponse.getAccessToken());
//
//		ConnectionModal connection = new ConnectionModal();
//		connection.setId(1L);
//		connection.setAccessToken(bearerTokenResponse.getAccessToken());
//		connection.setRealmeId(RealmID);
//		connection.setRefreshToken(refreshToken);
//		connectionService.save(connection);
//		IAuthorizer oauth = new OAuth2Authorizer(bearerTokenResponse.getAccessToken());
//		Config.setProperty(Config.BASE_URL_QBO, quickBooksConnectURL);
//		Context context = new Context(oauth, ServiceType.QBO, RealmID);
//		return new DataService(context);
//
//	}
}
