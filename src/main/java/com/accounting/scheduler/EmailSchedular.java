package com.accounting.scheduler;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.accounting.modal.EmailDetails;
import com.accounting.service.CustomerService;
import com.accounting.service.Emailservice;

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

    @Scheduled(cron = "0 * * ? * *")
    public String chartImageMalling()
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