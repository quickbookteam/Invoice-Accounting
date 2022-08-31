package com.accounting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accounting.entity.TaskDefinition;
import com.accounting.service.TaskSchedulingService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class JobSchedulingController {

    private TaskSchedulingService taskSchedulingService;
    
    @Autowired
    public JobSchedulingController(TaskSchedulingService taskSchedulingService) {
		this.taskSchedulingService = taskSchedulingService;
	}
	@PostMapping(path="/taskdef", consumes = "application/json", produces="application/json")
    public void addScheduler(@RequestBody TaskDefinition taskDefinition) {
    	taskSchedulingService.addAndUpdateScheduler(taskDefinition);
    	log.info("task added successfully");
    }
    @PutMapping("/updatescheduler")
	public void updateScheduler(@RequestBody TaskDefinition taskDefinition) {
    	taskSchedulingService.addAndUpdateScheduler(taskDefinition);
    	log.info("task updated successfully");
	}
}