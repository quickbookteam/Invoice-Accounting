package com.accounting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@AutoConfiguration
@EnableScheduling
@Slf4j
class InvoiceAccountingApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(InvoiceAccountingApplication.class, args);
   
		log.info("inside main Application started");

	}
}

