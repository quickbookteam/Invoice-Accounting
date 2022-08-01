package com.accounting.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.accounting.entity.Connection;
import com.accounting.service.ConnectionService;
import com.intuit.ipp.core.Context;
import com.intuit.ipp.core.ServiceType;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.security.IAuthorizer;
import com.intuit.ipp.security.OAuth2Authorizer;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.Config;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Component
public class Helper {
	
	
	private ConnectionService connectionService;
	
	@Autowired
	public void ConnectionService(@Qualifier("connectionImplementation")  ConnectionService connectionService)
	{
		this.connectionService=connectionService;
	}

	public DataService getConnection() throws FMSException {
		Connection connection = connectionService.getDetails();
		IAuthorizer oauth = new OAuth2Authorizer(connection.getAccessToken());
		Config.setProperty(Config.BASE_URL_QBO, "https://sandbox-quickbooks.api.intuit.com/v3/company");
		Context context = new Context(oauth, ServiceType.QBO, connection.getRealmId());
		return new DataService(context);
	}

}