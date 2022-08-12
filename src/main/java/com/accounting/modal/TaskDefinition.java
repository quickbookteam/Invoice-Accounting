package com.accounting.modal;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@lombok.Data
@Getter
@Setter
@AllArgsConstructor
@Document(collection = "Task")
public class TaskDefinition  {

    private String cronExpression;
    private String actionType;
    private String data;
    

	

}