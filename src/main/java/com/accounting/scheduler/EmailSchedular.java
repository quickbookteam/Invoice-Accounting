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
import com.accounting.service.SchedularService;

import lombok.extern.slf4j.Slf4j;

@EnableScheduling
@Configuration
@Slf4j
public class EmailSchedular {
	
	 
	 private  Emailservice emailService;

	 
	 private SchedularService schedularService;

    @Autowired // inject FirstServiceImpl
    public  EmailSchedular( Emailservice emailService,SchedularService schedularService) {
    	log.info("autowiring Email service", emailService);
        this.emailService = emailService;
        this.schedularService=schedularService;
    }

    //@Scheduled(cron = "0 * * ? * *")
    public void chartImageMalling()
    {
    	
    	if(schedularService.generateCharts())
    	{
    	EmailDetails details=new EmailDetails();
    	details.setRecipient("rusiapradhan33@gmail.com");
		details.setAttachment("D:\\charts\\Customerpie.jpg");
		details.setMsgBody("daily deport"+new Date());
		details.setSubject("daily customer report");
		String status= emailService.sendMailWithAttachment(details);
    	}
    	
    }

    
}