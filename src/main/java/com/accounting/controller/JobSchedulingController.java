package com.accounting.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accounting.modal.TaskDefinition;
import com.accounting.repositery.CustomerRepo;
import com.accounting.repositery.TaskRepo;
import com.accounting.service.Implimentation.TaskDefinitionBean;
import com.accounting.service.Implimentation.TaskSchedulingService;
import com.accounting.util.UUIDGenrater;

@RestController
public class JobSchedulingController {

    @Autowired
    private TaskSchedulingService taskSchedulingService;
    @Autowired
    private TaskDefinitionBean taskDefinitionBean;
    
    private final TaskRepo taskRepo;
   @Autowired 
   public JobSchedulingController(TaskRepo taskRepo){
	   this.taskRepo=taskRepo;
   }
   @PostMapping(path="/taskdef", consumes = "application/json", produces="application/json")
   public void scheduleATask(@RequestBody TaskDefinition taskDefinition) {
       taskDefinitionBean.setTaskDefinition(taskDefinition);
       taskRepo.save(taskDefinition);
       taskSchedulingService.scheduleATask(UUIDGenrater.Uuidgenrater(), taskDefinitionBean, taskDefinition.getCronExpression());
   }

    
//
//    @GetMapping(path="/remove/{jobid}")
//    public void removeJob(@PathVariable String jobid) {
//        taskSchedulingService.removeScheduledTask(jobid);
//    }
}