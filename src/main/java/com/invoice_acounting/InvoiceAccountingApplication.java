package com.invoice_acounting;

import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.invoice_acounting.config.QuickBookIntegration;

@SpringBootApplication
public class InvoiceAccountingApplication {
//	@Autowired
//	
//	QuickBookIntegration quickBookIntegration;

	public static void main(String[] args) throws Exception {
		ApplicationContext context =SpringApplication.run(InvoiceAccountingApplication.class, args);
		BasicConfigurator.configure();
		QuickBookIntegration quick= (QuickBookIntegration) context.getBean("calling");
		quick.demo();
//		InvoiceAccountingApplication obj=new InvoiceAccountingApplication();
//		obj.calling();
		System.out.println("Application Started");

	}
	@Bean
	public QuickBookIntegration calling()
	{
		return new QuickBookIntegration();
//		System.out.println(quickBookIntegration);
//		try {
//			quickBookIntegration.demo();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}


