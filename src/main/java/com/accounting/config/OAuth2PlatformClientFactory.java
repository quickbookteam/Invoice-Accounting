package com.accounting.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.accounting.util.UtilConstants;
import com.intuit.oauth2.client.OAuth2PlatformClient;
import com.intuit.oauth2.config.Environment;
import com.intuit.oauth2.config.OAuth2Config;



@Service
@PropertySource(value="classpath:/application.properties", ignoreResourceNotFound=true)
public class OAuth2PlatformClientFactory {
	
	@Autowired
	org.springframework.core.env.Environment env;

	private OAuth2PlatformClient client;
	private OAuth2Config oauth2Config;
	
	@PostConstruct
	public void init() {
		//initialize the config
		oauth2Config = new OAuth2Config.OAuth2ConfigBuilder(UtilConstants.clientId, UtilConstants.clientSecret) //set client id, secret
				.callDiscoveryAPI(Environment.SANDBOX) // call discovery API to populate urls
				.buildConfig();
		//build the client
		client  = new OAuth2PlatformClient(oauth2Config);
	}
	
	
	public OAuth2PlatformClient getOAuth2PlatformClient()  {
		return client;
	}
	
	public OAuth2Config getOAuth2Config()  {
		System.out.println(oauth2Config.getIntuitAuthorizationEndpoint());
		return oauth2Config;
	}
	
	public String getPropertyValue(String proppertyName) {
		return env.getProperty(proppertyName);
	}

}
