package com.invoice_acounting;

import org.apache.log4j.BasicConfigurator;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.intuit.ipp.services.DataService;
import com.invoice_acounting.config.QuickBookIntegration;

@SpringBootApplication
@AutoConfiguration
public class InvoiceAccountingApplication {


	public static void main(String[] args) throws Exception {
		ApplicationContext context =SpringApplication.run(InvoiceAccountingApplication.class, args);
		BasicConfigurator.configure();
		QuickBookIntegration quick= (QuickBookIntegration) context.getBean("calling");
//		quick.onlineIntegartion();
		System.out.println("Application Started");
	}
	
	@Bean
	public QuickBookIntegration calling()
	{
		return new QuickBookIntegration();
	}
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
     
	

}


