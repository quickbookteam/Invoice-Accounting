package com.invoice_acounting.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.intuit.ipp.data.Customer;
import com.invoice_acounting.entity.customer.LocalCustomer;
import com.invoice_acounting.modal.customer.CustomerModal;
import com.invoice_acounting.repositery.CustomerRepo;
import com.invoice_acounting.util.Helper;

@Component
public class CustomerDao {
	
	@Autowired
	CustomerRepo customerRepo; 
	
	@Autowired
	Helper helper;
	
	public ResponseEntity<?> save(CustomerModal customer){	
		LocalCustomer localCustomer =  helper.getMapper().map(customer, LocalCustomer.class);
		localCustomer.setStatus("created");
		localCustomer.setCustomer_id("0");
		customerRepo.save(localCustomer);
		return new ResponseEntity<>(localCustomer,HttpStatus.OK);
		
	}

	public CustomerModal get(String id) {
		Optional<?> optionalCustomer = customerRepo.findById(id);
		if(!optionalCustomer.isEmpty()) {
			LocalCustomer customer = (LocalCustomer) optionalCustomer.get();
			CustomerModal customerModal = helper.getMapper().map(customer, CustomerModal.class);
			return customerModal;
		}
		return  null;
	}

	public ResponseEntity<?> delete(String id) {
		Optional<?> optionalCustomer = customerRepo.findById(id);
		LocalCustomer localCustomer = new LocalCustomer();
		localCustomer.set_id(id);
		if(!optionalCustomer.isEmpty()) {
			customerRepo.delete(localCustomer);
		}
		return new ResponseEntity("Customer not valid",HttpStatus.BAD_REQUEST);
	}
	
	public List<CustomerModal> getAll(){
		List<LocalCustomer> customerAll = customerRepo.findAll();
		List<CustomerModal> customerModalList =new ArrayList<>();
		if(customerAll.size()<1) {
			return null;
		}
		for(LocalCustomer customer: customerAll ) {
			CustomerModal customerModal =  helper.getMapper().map(customer, CustomerModal.class);
			customerModalList.add(customerModal);
		}
		return customerModalList;
	}

	public CustomerModal update(String id, CustomerModal customer) {
		if(customerRepo.existsById(id)){
			customerRepo.save(helper.getMapper().map(customer, LocalCustomer.class));
		}
		return customer;
	}
}
