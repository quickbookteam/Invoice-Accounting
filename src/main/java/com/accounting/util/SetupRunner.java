package com.accounting.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.accounting.entity.TaskDefinition;
import com.accounting.entity.User;
import com.accounting.repositery.UserRepository;
import com.accounting.scheduler.ConnectionSchedular;
import com.accounting.service.TaskSchedulingService;

@Component
public class SetupRunner implements CommandLineRunner {

	private ConnectionSchedular connectionSchedular;
	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private TaskSchedulingService taskSchedulingService;

	@Autowired
	public SetupRunner(ConnectionSchedular connectionSchedular ,UserRepository userRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder,TaskSchedulingService taskSchedulingService) {
		this.connectionSchedular = connectionSchedular;
		this.userRepository=userRepository;
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
		this.taskSchedulingService=taskSchedulingService;

	}

	public void run(String... args) throws Exception {
		connectionSchedular.connectionStablished();
		User user=User.builder()
				.userName("admin")
				.password(this.bCryptPasswordEncoder.encode("admin"))
				.build();
		this.userRepository.save(user);
		TaskDefinition emailTask=new TaskDefinition();
		emailTask.setActionType(UtilConstants.Email);
		emailTask.setCronExpression(UtilConstants.CRON);
		this.taskSchedulingService.addAndUpdateScheduler(emailTask);
		TaskDefinition customerTask=new TaskDefinition();
		customerTask.setActionType(UtilConstants.Customer);
		customerTask.setCronExpression(UtilConstants.CRON);
		this.taskSchedulingService.addAndUpdateScheduler(customerTask);
		TaskDefinition invoiceTask=new TaskDefinition();
		invoiceTask.setActionType(UtilConstants.Invoice);
		invoiceTask.setCronExpression(UtilConstants.CRON);
		this.taskSchedulingService.addAndUpdateScheduler(invoiceTask);
        
	}
	}
