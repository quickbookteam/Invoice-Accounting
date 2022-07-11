package com.invoice_acounting;


import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

@SpringBootApplication
public class InvoiceAccountingApplication {

//	@Value("${OAuth2AppClientId}")
	private String clientId="ABPKq5IgAdjjgeMzfOYaNS1mzP8JB84vzvOXJPglewU6mCQTfT";

//	@Value("${OAuth2AppClientSecret}")
	private String clientSecret="iT52ZkqyL1TwjYt5bNBdK7XnSIqNMLcY4tQeAjjZ";

//	@Value("${refreshToken}")
	private String refreshToken="AB11666261255Il4acFcncA4b8LCkIspZMAwdgL8xN4U4j46rc";

//	@Value("${RealmID}")
	private String RealmID="4620816365233270410";
	

	public static void main(String[] args) {
		SpringApplication.run(InvoiceAccountingApplication.class, args);
		BasicConfigurator.configure();

		InvoiceAccountingApplication obj = new InvoiceAccountingApplication();
		try {
			obj.demo();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

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
			System.out.println(bearerTokenResponse.getAccessToken());
			isRetry = false;
		} catch (Exception e) {
			throw e;
		}

		IAuthorizer oauth = new OAuth2Authorizer(bearerTokenResponse.getAccessToken());
		Config.setProperty(Config.BASE_URL_QBO, quickBooksConnectURL);
		Context context = new Context(oauth, ServiceType.QBO, RealmID);

		return new DataService(context);

	}
}
