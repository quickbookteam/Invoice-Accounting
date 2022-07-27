package com.invoice_acounting;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@AutoConfiguration
@Slf4j
public class InvoiceAccountingApplication {


	public static void main(String[] args) throws Exception {
		ApplicationContext context =SpringApplication.run(InvoiceAccountingApplication.class, args);
		BasicConfigurator.configure();

//		log.info("inside main Application started");
		System.out.println("Application Started");
	}
	

	

}


