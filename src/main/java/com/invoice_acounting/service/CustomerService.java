package com.invoice_acounting.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.invoice_acounting.entity.customer.LocalCustomer;
import com.invoice_acounting.modal.customer.CustomerModal;

@Service
public interface CustomerService {
	
	ResponseEntity<?> save(CustomerModal customerModal);

	CustomerModal get(String id);
	
	public ResponseEntity<?> delete(String id);
	
	public List<CustomerModal> getAll();
	
	public CustomerModal update(String id, CustomerModal customer);
	
}
