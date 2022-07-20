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
import com.invoice_acounting.modal.Connection;
import com.invoice_acounting.service.Implimentation.ConnectionServiceImpl;

@Component
public class QuickBookIntegration {
//  @Value("${OAuth2AppClientId}")
  private String clientId="ABU0pV6Wc9PWCs3UhP0hsqvUyoA9vopY3yjT8oTmNrgggizNs5";

  //    @Value("${OAuth2AppClientSecret}")
  private String clientSecret="KIMAYJDdSuGO3Sgc9Y7phwUH1kD9dsKLDvl6kXvQ";

  //    @Value("${refreshToken}")
  private String refreshToken="AB11667023347K1n2OYm0U9LJ4lvV1elxkGtrveaulmfVepuPs";

  //    @Value("${RealmID}")
  
  private String RealmID="4620816365222374190";
  
  @Autowired 
  ConnectionServiceImpl connectionServiceImpl;

	public DataService demo() throws Exception {

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
		
		Connection connection = new Connection();
		connection.setId(1L);
		connection.setAccessToken(bearerTokenResponse.getAccessToken());
		connection.setRealmeId(RealmID);
		connection.setRefreshToken(refreshToken);
		connectionServiceImpl.save(connection);
		IAuthorizer oauth = new OAuth2Authorizer(bearerTokenResponse.getAccessToken());
		Config.setProperty(Config.BASE_URL_QBO, quickBooksConnectURL);
		Context context = new Context(oauth, ServiceType.QBO, RealmID);
	return new DataService(context);

	}
}
