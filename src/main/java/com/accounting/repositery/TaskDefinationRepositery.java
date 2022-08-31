package com.accounting.repositery;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.accounting.entity.TaskDefinition;


@Repository
public  interface TaskDefinationRepositery extends MongoRepository<TaskDefinition, String> {
	 public TaskDefinition findByactionType(String mail);
}
