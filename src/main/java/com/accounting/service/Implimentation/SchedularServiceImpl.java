package com.accounting.service.Implimentation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.accounting.modal.customer.LocalCustomerModal;
import com.accounting.repositery.CustomerRepo;
import com.accounting.service.ConnectionService;
import com.accounting.service.SchedularService;
import com.accounting.util.Helper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;

@Service
@Qualifier("schedularServiceImpl")
public class SchedularServiceImpl implements SchedularService {

	private Helper helper;
	private ObjectMapper mapper;
    public SchedularServiceImpl(@Qualifier("helper") Helper helper) {
    this.helper=helper;
    this.mapper=new ObjectMapper();
	}

	 @Override
	    public Customer saveCustomerToQuickBook(LocalCustomerModal customerModal) throws FMSException {
	        DataService dataService = helper.getConnection();

	        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	        Customer customer = mapper.convertValue(customerModal, Customer.class);
	        return dataService.add(customer);     
	    }
}
