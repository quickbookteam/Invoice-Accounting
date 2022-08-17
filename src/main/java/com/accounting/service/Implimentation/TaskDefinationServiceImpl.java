package com.accounting.service.Implimentation;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.fields;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accounting.entity.TaskDefinition;
import com.accounting.entity.customer.LocalCustomer;
import com.accounting.exception.CustomException;
import com.accounting.exception.CustomerNotFoundException;
import com.accounting.modal.CommonResponse;
import com.accounting.modal.Data;
import com.accounting.modal.customer.CustomerModal;
import com.accounting.modal.customer.LocalCustomerModal;
import com.accounting.repositery.CustomerRepo;
import com.accounting.repositery.TaskDefinationRepositery;
import com.accounting.service.CustomerService;
import com.accounting.service.TaskDefinationService;
import com.accounting.util.ChartHelper;
import com.accounting.util.UtilConstants;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service

public class TaskDefinationServiceImpl implements TaskDefinationService {

	public final TaskDefinationRepositery taskDefinationRepositery;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	public TaskDefinationServiceImpl(TaskDefinationRepositery taskDefinationRepositery) {
		this.taskDefinationRepositery=taskDefinationRepositery;
	}

	public ResponseEntity<CommonResponse> update(TaskDefinition taskDefination,String jobId) {
			TaskDefinition task =taskDefinationRepositery.findByactionType(taskDefination.getActionType());
		if (task != null) {
			task.setCronExpression(taskDefination.getCronExpression());
			task.setJobId(jobId);
			taskDefinationRepositery.save(task);
			CommonResponse response = new CommonResponse(task, UtilConstants.CUSTOMER_UPDATED);
			return new ResponseEntity<CommonResponse>(response, HttpStatus.ACCEPTED);
		}
		throw new CustomerNotFoundException(UtilConstants.CUSTOMER_NOT_FOUND);
	}

	

}
