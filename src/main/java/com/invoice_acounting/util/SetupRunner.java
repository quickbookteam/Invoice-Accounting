package com.invoice_acounting.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.invoice_acounting.scheduler.ConnectionSchedular;

@Component
public class SetupRunner 
	implements CommandLineRunner
{
	

	private ConnectionSchedular connectionSchedular;

	@Autowired
	public SetupRunner(ConnectionSchedular connectionSchedular) {
		this.connectionSchedular = connectionSchedular;
		

	}
	
	public void run(String... args) throws Exception {
		connectionSchedular.connectionStablished();

}
}
