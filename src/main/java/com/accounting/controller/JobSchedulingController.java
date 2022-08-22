package com.accounting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accounting.entity.TaskDefinition;
import com.accounting.modal.CommonResponse;
import com.accounting.modal.customer.CustomerModal;
import com.accounting.repositery.TaskDefinationRepositery;
import com.accounting.scheduler.CustomerScheduler;
import com.accounting.scheduler.EmailSchedular;
import com.accounting.scheduler.InvoiceScheduler;
import com.accounting.service.TaskDefinationService;
import com.accounting.service.Implimentation.TaskSchedulingService;
import com.accounting.util.UtilConstants;
import com.accounting.util.UuidGenerator;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class JobSchedulingController {

    private TaskSchedulingService taskSchedulingService;
    private TaskDefinationRepositery taskDefinationRepositery;  
    private TaskDefinationService taskDefinationService; 
    private EmailSchedular emailSchedular;
    private CustomerScheduler customerScheduler;
    private InvoiceScheduler invoiceScheduler;
    @Autowired
    public JobSchedulingController(TaskDefinationService taskDefinationService,
    		TaskDefinationRepositery taskDefinationRepositery,TaskSchedulingService taskSchedulingService,
    		EmailSchedular emailSchedular,CustomerScheduler customerScheduler,InvoiceScheduler invoiceScheduler){
    	
    	this.emailSchedular=emailSchedular;
    	this.taskDefinationRepositery=taskDefinationRepositery;
    	this.taskDefinationService=taskDefinationService;
    	this.taskSchedulingService=taskSchedulingService;
    	this.customerScheduler=customerScheduler;
    	this.invoiceScheduler=invoiceScheduler;
    }
    
    @PostMapping(path="/taskdef", consumes = "application/json", produces="application/json")
    public void scheduleATask(@RequestBody TaskDefinition taskDefinition) {
    	
    	taskDefinition.setJobId(UuidGenerator.Uuidgenrater());
        taskDefinationRepositery.save(taskDefinition);
        TaskDefinition cron=taskDefinationRepositery.findByactionType(taskDefinition.getActionType());
        if(taskDefinition.getActionType().equalsIgnoreCase(UtilConstants.Email)) {
          taskSchedulingService.scheduleATask(cron.getJobId(), emailSchedular,cron.getCronExpression());
        }
        else if(taskDefinition.getActionType().equalsIgnoreCase(UtilConstants.Customer)) {
        	 taskSchedulingService.scheduleATask(cron.getJobId(), customerScheduler,cron.getCronExpression());
        }
        else if(taskDefinition.getActionType().equalsIgnoreCase(UtilConstants.Invoice)) {
        	taskSchedulingService.scheduleATask(cron.getJobId(), invoiceScheduler,cron.getCronExpression());
        }
        else
        	{log.info("enter valid scheduler name");}
    }
    @PutMapping("/updatescheduler")
	public void updateScheduler(@RequestBody TaskDefinition taskDefinition) {
    	TaskDefinition cron=taskDefinationRepositery.findByactionType(taskDefinition.getActionType());
    	 taskSchedulingService.removeScheduledTask(cron.getJobId());
       	taskDefinationService.update(taskDefinition,UuidGenerator.Uuidgenrater());
    	TaskDefinition updatedCron=taskDefinationRepositery.findByactionType(taskDefinition.getActionType());
    	 if(taskDefinition.getActionType().equalsIgnoreCase(UtilConstants.Email)) {
    		 taskSchedulingService.scheduleATask(updatedCron.getJobId(), emailSchedular,updatedCron.getCronExpression());
    	 }
           else if(taskDefinition.getActionType().equalsIgnoreCase(UtilConstants.Customer)) {
        	   taskSchedulingService.scheduleATask(updatedCron.getJobId(), customerScheduler,updatedCron.getCronExpression());
           }
           else if(taskDefinition.getActionType().equalsIgnoreCase(UtilConstants.Invoice)) {
        	   taskSchedulingService.scheduleATask(updatedCron.getJobId(), invoiceScheduler,updatedCron.getCronExpression());
           }
           else {
           	log.info("enter valid scheduler name");
           }
	}
}