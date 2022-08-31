package com.accounting.connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accounting.entity.Connection;
import com.accounting.exception.CustomException;
import com.accounting.service.ConnectionService;
import com.accounting.util.UtilConstants;
import com.intuit.ipp.core.Context;
import com.intuit.ipp.core.ServiceType;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.security.IAuthorizer;
import com.intuit.ipp.security.OAuth2Authorizer;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.Config;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class QuickBookConnection implements IConnections {
	
	private ConnectionService connectionService;

	@Autowired
	public void ConnectionService(ConnectionService connectionService) {
		this.connectionService = connectionService;
	}

	public DataService createConnection() {
		try {
			log.info("Inside connection creation");
		Connection connection = connectionService.getDetails();
		IAuthorizer oauth = new OAuth2Authorizer(connection.getAccessToken());
		Config.setProperty(Config.BASE_URL_QBO, UtilConstants.quickBooksConnectURL);
		Context context = new Context(oauth, ServiceType.QBO, connection.getRealmId());
		return new DataService(context);
		}catch(Exception E) {
			log.info("inside create connection catch block");
			throw new CustomException(E.getMessage());
		}
	}
}
