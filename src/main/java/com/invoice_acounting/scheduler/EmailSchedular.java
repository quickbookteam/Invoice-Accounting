package com.invoice_acounting.scheduler;

import java.util.Date;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.intuit.ipp.core.Context;
import com.intuit.ipp.core.ServiceType;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.security.IAuthorizer;
import com.intuit.ipp.security.OAuth2Authorizer;
import com.intuit.ipp.util.Config;
import com.intuit.oauth2.client.OAuth2PlatformClient;
import com.intuit.oauth2.config.OAuth2Config;
import com.intuit.oauth2.data.BearerTokenResponse;
import com.intuit.oauth2.exception.OAuthException;
import com.invoice_acounting.modal.ConnectionModal;
import com.invoice_acounting.modal.EmailDetails;
import com.invoice_acounting.service.ConnectionService;
import com.invoice_acounting.service.CustomerService;
import com.invoice_acounting.service.Emailservice;
import com.invoice_acounting.util.UtilContants;

import lombok.extern.slf4j.Slf4j;

@EnableScheduling
@Configuration
@Slf4j
public class EmailSchedular {
	
	 @Autowired
	 private Emailservice emailService;

	 @Autowired
	 CustomerService customerService;

//    @Autowired // inject FirstServiceImpl
//    public void ConnectionService(@Qualifier("connectionImplementation") ConnectionService connectionService) {
//    	log.info("autowiring Email service", connectionService);
//        this.connectionService = connectionService;
//    }

    @Scheduled(cron = "0 * * ? * *")
    public String connectionStablished()
    {
    	
        customerService.generateCharts();
    	EmailDetails details=new EmailDetails();
    	details.setRecipient("rusiapradhan33@gmail.com");
		details.setAttachment("D:\\charts\\Customerpie.jpg");
		details.setMsgBody("daily deport"+new Date());
		details.setSubject("daily customer report");
       
    	String status= emailService.sendMailWithAttachment(details);

        return status;
    }


}
