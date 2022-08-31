package com.accounting.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@lombok.Data
public class TaskDefinition {
	@Id
	private String id;	
	@Indexed(unique = true)
	private String actionType;
    private String cronExpression;
    private String jobId;
   
}