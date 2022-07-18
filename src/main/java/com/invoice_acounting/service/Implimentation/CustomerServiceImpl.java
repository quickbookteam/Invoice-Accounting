package com.invoice_acounting.service.Implimentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.invoice_acounting.dao.CustomerDao;
import com.invoice_acounting.entity.customer.Customer;
import com.invoice_acounting.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerDao customerdao;

	@Override
	public ResponseEntity<?> save(Customer customer) {
		return customerdao.save(customer);
	}
	

}
