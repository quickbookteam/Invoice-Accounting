package com.invoice_acounting;

<<<<<<< HEAD
<<<<<<< HEAD

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.ipp.core.Context;
import com.intuit.ipp.core.ServiceType;
import com.intuit.ipp.data.CompanyInfo;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.security.IAuthorizer;
import com.intuit.ipp.security.OAuth2Authorizer;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.services.QueryResult;
import com.intuit.ipp.util.Config;
import com.intuit.oauth2.client.OAuth2PlatformClient;
import com.intuit.oauth2.config.Environment;
import com.intuit.oauth2.config.OAuth2Config;
import com.intuit.oauth2.data.BearerTokenResponse;
import com.intuit.oauth2.exception.OAuthException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
=======
import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> Siddharth
=======
import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> Siddharth
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.intuit.ipp.services.DataService;
import com.invoice_acounting.config.QuickBookIntegration;

@SpringBootApplication
public class InvoiceAccountingApplication {
//	@Autowired
//	QuickBookIntegration quickBookIntegration;

	private static final Logger logger = Logger.getLogger(InvoiceAccountingApplication.class);

	//	@Value("${OAuth2AppClientId}")
	private String clientId="ABU0pV6Wc9PWCs3UhP0hsqvUyoA9vopY3yjT8oTmNrgggizNs5";

	//	@Value("${OAuth2AppClientSecret}")
	private String clientSecret="KIMAYJDdSuGO3Sgc9Y7phwUH1kD9dsKLDvl6kXvQ";

	//	@Value("${refreshToken}")
	private String refreshToken="AB11666332535MwuDVbgxxSlwGQmLO8A708zbatMqsWHDNhDg1";

	//	@Value("${RealmID}")
	
	private String RealmID="4620816365222374190";

	@Value("${check}")
	static String check;
	
	public static void main(String[] args) {
		SpringApplication.run(InvoiceAccountingApplication.class, args);
<<<<<<< HEAD
<<<<<<< HEAD

		BasicConfigurator.configure();
		
		
		
		InvoiceAccountingApplication obj=new InvoiceAccountingApplication();
		try {
			obj.demo();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
//
public String demo() throws Exception {

	
	
	
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
//			System.out.println(bearerTokenResponse.getAccessToken());
		isRetry = false;
	} catch (Exception e) {
		throw e;
	}

	IAuthorizer oauth = new OAuth2Authorizer(bearerTokenResponse.getAccessToken());
	Config.setProperty(Config.BASE_URL_QBO, quickBooksConnectURL);
	Context context = new Context(oauth, ServiceType.QBO, RealmID);
	// get all companyinfo
	String sql = "select * from companyinfo";
	QueryResult queryResult = new DataService(context).executeQuery(sql);


	System.out.println(processResponse("done",queryResult));

	return processResponse("done", queryResult);
//	System.out.println(queryResult);


//		String sql = "select * from companyinfo";
//        QueryResult queryResult =dataService.executeQuery(sql);
//        System.out.println(queryResult);

//	return new DataService(context);

}


	private String processResponse(String failureMsg, QueryResult queryResult) {
		if (!queryResult.getEntities().isEmpty() && queryResult.getEntities().size() > 0) {
			CompanyInfo companyInfo = (CompanyInfo) queryResult.getEntities().get(0);
			logger.info("Companyinfo -> CompanyName: " + companyInfo.getCompanyName());
			ObjectMapper mapper = new ObjectMapper();
			try {
				String jsonInString = mapper.writeValueAsString(companyInfo);
				return jsonInString;
			} catch (JsonProcessingException e) {
				logger.error("Exception while getting company info ", e);
				return new JSONObject().put("response", failureMsg).toString();
			}

		}
		return failureMsg;
	}
}

=======
		BasicConfigurator.configure();

	}
//	@Bean
//	public DataService getDataService()
//	{
//		try {
//			return quickBookIntegration.demo();
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//			return null;
//		}
//		 
//	}
}


>>>>>>> Siddharth
=======
		BasicConfigurator.configure();

	}
//	@Bean
//	public DataService getDataService()
//	{
//		try {
//			return quickBookIntegration.demo();
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//			return null;
//		}
//		 
//	}
}


>>>>>>> Siddharth
