package com.invoice_acounting.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.invoice_acounting.entity.customer.Customer;
import com.invoice_acounting.repositery.CustomerRepo;

@Component
public class CustomerDao {
	
	@Autowired
	CustomerRepo customerRepo; 
	
	public ResponseEntity<Customer> save(Customer customer){
		customerRepo.save(customer);
		return new ResponseEntity<>(customer,HttpStatus.OK);
		
	}
	
}
