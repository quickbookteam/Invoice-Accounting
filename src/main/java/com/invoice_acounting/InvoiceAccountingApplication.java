package com.invoice_acounting;

import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.intuit.ipp.services.DataService;
import com.invoice_acounting.config.QuickBookIntegration;

@SpringBootApplication
public class InvoiceAccountingApplication {
//	@Autowired
//	QuickBookIntegration quickBookIntegration;

	public static void main(String[] args) {
		SpringApplication.run(InvoiceAccountingApplication.class, args);
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


