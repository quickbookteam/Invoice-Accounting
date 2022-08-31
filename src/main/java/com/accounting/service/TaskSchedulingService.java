package com.accounting.service;

import com.accounting.entity.TaskDefinition;

public interface TaskSchedulingService {

    public void scheduleATask(String jobId, Runnable tasklet, String cronExpression);
    public void removeScheduledTask(String jobId);
    public void addAndUpdateScheduler(TaskDefinition taskDefinition);
}