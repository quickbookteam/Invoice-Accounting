package com.invoice_acounting.service.Implimentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.invoice_acounting.dao.CustomerDao;
import com.invoice_acounting.entity.customer.LocalCustomer;
import com.invoice_acounting.modal.customer.CustomerModal;
import com.invoice_acounting.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerDao customerdao;

	@Override
	public ResponseEntity<?> save(CustomerModal customerModal) {
		return customerdao.save(customerModal);
	}

	@Override
	public CustomerModal get(String id) {
		return customerdao.get(id);
	}

	public ResponseEntity<?> delete(String id) {
		return customerdao.delete(id);
	}

	@Override
	public List<CustomerModal> getAll() {
		return customerdao.getAll();
	}

	public CustomerModal update(String id, CustomerModal customer) {
		return customerdao.update(id,customer);
	}
	
}
