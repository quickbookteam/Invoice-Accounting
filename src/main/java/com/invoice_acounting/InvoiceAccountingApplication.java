package com.invoice_acounting;

import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.ipp.core.Context;
import com.intuit.ipp.core.ServiceType;
import com.intuit.ipp.data.CompanyInfo;
import com.intuit.ipp.security.IAuthorizer;
import com.intuit.ipp.security.OAuth2Authorizer;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.services.QueryResult;
import com.intuit.ipp.util.Config;
import com.intuit.oauth2.client.OAuth2PlatformClient;
import com.intuit.oauth2.config.Environment;
import com.intuit.oauth2.config.OAuth2Config;
import com.intuit.oauth2.data.BearerTokenResponse;
import com.invoice_acounting.service.QBOServiceHelper;

@SpringBootApplication
@ComponentScan("com.invoice_acounting")
public class InvoiceAccountingApplication {

//	@Value("${OAuth2AppClientId}")
//	private String clientId;

	private static final Logger logger = Logger.getLogger(InvoiceAccountingApplication.class);

	private String clientId = "ABPKq5IgAdjjgeMzfOYaNS1mzP8JB84vzvOXJPglewU6mCQTfT";

//	@Value("${OAuth2AppClientSecret}")
	private String clientSecret = "iT52ZkqyL1TwjYt5bNBdK7XnSIqNMLcY4tQeAjjZ";

//	@Value("${refreshToken}")
	private String refreshToken = "AB11666261255Il4acFcncA4b8LCkIspZMAwdgL8xN4U4j46rc";

//	@Value("${RealmID}")
	private String RealmID = "4620816365233270410";
	
	  @Autowired
	    public QBOServiceHelper helper;

	public static void main(String[] args) {
		SpringApplication.run(InvoiceAccountingApplication.class, args);
		BasicConfigurator.configure();

//		System.setProperty("abc", )

//		 Properties props = new Properties();
//	        props.put("abc", "abc-value");
//	        System.setProperties(props);
//	        String x=System.getProperty("abc");
//	        System.out.println(x);

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
//			System.out.println(bearerTokenResponse.getAccessToken());
			isRetry = false;
		} catch (Exception e) {
			throw e;
		}

		IAuthorizer oauth = new OAuth2Authorizer(bearerTokenResponse.getAccessToken());
		Config.setProperty(Config.BASE_URL_QBO, quickBooksConnectURL);
		Context context = new Context(oauth, ServiceType.QBO, RealmID);
   System.out.println(helper);
        DataService service = this.helper.getDataService(RealmID, bearerTokenResponse.getAccessToken());
		// get all companyinfo
		String sql = "select * from companyinfo";
		QueryResult queryResult = service.executeQuery(sql);

		System.out.println(queryResult);
		
		
//		String sql = "select * from companyinfo";
//        QueryResult queryResult =dataService.executeQuery(sql);
//        System.out.println(queryResult); 

		return new DataService(context);

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
