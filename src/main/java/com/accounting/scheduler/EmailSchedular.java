package com.accounting.scheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.accounting.modal.ConnectionModal;
import com.accounting.modal.EmailDetails;
import com.accounting.service.ConnectionService;
import com.accounting.service.CustomerService;
import com.accounting.service.Emailservice;
import com.accounting.service.Implimentation.EmailServiceImp;
import com.accounting.util.UtilContants;
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

import lombok.extern.slf4j.Slf4j;

@EnableScheduling
@Configuration
@Slf4j
public class EmailSchedular {
	
	 
	 private  Emailservice emailService;

	 
	 private CustomerService customerService;

    @Autowired // inject FirstServiceImpl
    public void Emailservice(@Qualifier("emailService") Emailservice emailService,@Qualifier("customerServiceImplementation") CustomerService customerService) {
    	log.info("autowiring Email service", emailService);
        this.emailService = emailService;
        this.customerService=customerService;
    }

    //@Scheduled(cron = "0 * * ? * *")
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
