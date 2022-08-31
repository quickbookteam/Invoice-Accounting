package com.accounting.service.Implimentation;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import com.accounting.entity.TaskDefinition;
import com.accounting.exception.CustomException;
import com.accounting.repositery.TaskDefinationRepositery;
import com.accounting.scheduler.CustomerScheduler;
import com.accounting.scheduler.EmailSchedular;
import com.accounting.scheduler.InvoiceScheduler;
import com.accounting.service.TaskSchedulingService;
import com.accounting.util.UtilConstants;
import com.accounting.util.UuidGenerator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TaskSchedulingServiceimpl implements TaskSchedulingService {

    private TaskScheduler taskScheduler;
    private TaskDefinationRepositery taskDefinationRepositery;  
    private EmailSchedular emailSchedular;
    private CustomerScheduler customerScheduler;
    private InvoiceScheduler invoiceScheduler;
    Map<String, ScheduledFuture<?>> jobsMap = new HashMap<>();

    @Autowired
    public TaskSchedulingServiceimpl(TaskScheduler taskScheduler, TaskDefinationRepositery taskDefinationRepositery,
			EmailSchedular emailSchedular, CustomerScheduler customerScheduler, InvoiceScheduler invoiceScheduler,
			Map<String, ScheduledFuture<?>> jobsMap) {
		this.taskScheduler = taskScheduler;
		this.taskDefinationRepositery = taskDefinationRepositery;
		this.emailSchedular = emailSchedular;
		this.customerScheduler = customerScheduler;
		this.invoiceScheduler = invoiceScheduler;
		this.jobsMap = jobsMap;
	}

	
    public void scheduleATask(String jobId, Runnable tasklet, String cronExpression) {
        ScheduledFuture<?> scheduledTask = taskScheduler.schedule(tasklet, new CronTrigger(cronExpression, TimeZone.getTimeZone(TimeZone.getDefault().getID())));
        jobsMap.put(jobId, scheduledTask);
    }

    public void removeScheduledTask(String jobId) {
        ScheduledFuture<?> scheduledTask = jobsMap.get(jobId);
        if(scheduledTask != null) {
            scheduledTask.cancel(true);
            jobsMap.put(jobId, null);
        }
    }

	@Override
	public void addAndUpdateScheduler(TaskDefinition taskDefinition) {
		taskDefinition.setJobId(UuidGenerator.Uuidgenrater());
		TaskDefinition cron=taskDefinationRepositery.findByactionType(taskDefinition.getActionType());
		if(cron!=null) {
   	      removeScheduledTask(cron.getJobId());
   	      taskDefinationRepositery.deleteById(cron.getId());
		}
        taskDefinationRepositery.save(taskDefinition);
        TaskDefinition updatedCron=taskDefinationRepositery.findByactionType(taskDefinition.getActionType());
        if(taskDefinition.getActionType().equalsIgnoreCase(UtilConstants.Email)) {
          scheduleATask(updatedCron.getJobId(), emailSchedular,updatedCron.getCronExpression());
        }
        else if(taskDefinition.getActionType().equalsIgnoreCase(UtilConstants.Customer)) {
        	 scheduleATask(updatedCron.getJobId(), customerScheduler,updatedCron.getCronExpression());
        }
        else if(taskDefinition.getActionType().equalsIgnoreCase(UtilConstants.Invoice)) {
        	scheduleATask(updatedCron.getJobId(), invoiceScheduler,updatedCron.getCronExpression());
        }
        else{
        	log.info(UtilConstants.Scheduler_failed);
        	throw new CustomException(UtilConstants.Scheduler_failed);
        	}
		
	}
		
}