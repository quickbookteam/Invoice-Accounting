package com.invoice_acounting.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.invoice_acounting.entity.customer.Customer;

@Service
public interface CustomerService {
	
	ResponseEntity<?> save(Customer customer);
}
