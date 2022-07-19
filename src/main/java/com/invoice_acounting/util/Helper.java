package com.invoice_acounting.util;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

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
import com.invoice_acounting.modal.Connection;
import com.invoice_acounting.service.Implimentation.ConnectionServiceImpl;
@Component
public class Helper {
	
	@Autowired
	ConnectionServiceImpl connectionService; 
	
	@Bean
	public ModelMapper getMapper() {
		return new ModelMapper();
	}
	
	private static final Logger logger = Logger.getLogger(Helper.class);
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
	
	public DataService getConnection() throws FMSException {
		Connection connection = connectionService.get(1L); 
		IAuthorizer oauth = new OAuth2Authorizer(connection.getAccessToken());
		Config.setProperty(Config.BASE_URL_QBO, "https://sandbox-quickbooks.api.intuit.com/v3/company");
		Context context = new Context(oauth, ServiceType.QBO, connection.getRealmeId());
	return new DataService(context);
	}
	
	
}
