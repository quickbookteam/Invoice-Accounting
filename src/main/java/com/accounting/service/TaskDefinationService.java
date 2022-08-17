package com.accounting.service;

import org.springframework.http.ResponseEntity;

import com.accounting.entity.TaskDefinition;
import com.accounting.modal.CommonResponse;

public interface TaskDefinationService {

	ResponseEntity<CommonResponse> update(TaskDefinition taskDefinition,String jobId);
}
