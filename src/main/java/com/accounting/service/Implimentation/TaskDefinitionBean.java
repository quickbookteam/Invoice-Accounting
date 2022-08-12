package com.accounting.service.Implimentation;

import org.springframework.stereotype.Service;

import com.accounting.modal.TaskDefinition;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TaskDefinitionBean implements Runnable {

    private TaskDefinition taskDefinition;

    @Override
    public void run() {
        log.info(taskDefinition.getCronExpression());
    }

    public TaskDefinition getTaskDefinition() {
        return taskDefinition;
    }

    public void setTaskDefinition(TaskDefinition taskDefinition) {
        this.taskDefinition = taskDefinition;
    }
}