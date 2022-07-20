package com.invoice_acounting.service;

import java.util.List;

import com.intuit.ipp.data.Customer;
import com.intuit.ipp.exception.FMSException;
import com.invoice_acounting.entity.customer.LocalCustomer;
import com.invoice_acounting.entity.invoice.LocalInvoice;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.invoice_acounting.modal.customer.CustomerModal;

@Service
public interface CustomerService {
	
	ResponseEntity<?> save(CustomerModal customerModal);

	CustomerModal get(String id);
	
	public ResponseEntity<?> delete(String id);
	
	public List<CustomerModal> getAll();
	
	public CustomerModal update(String id, CustomerModal customer);

	public CustomerModal findById(String id);
	public List<LocalCustomer> findAllLocalCustomers();

	public Customer saveCustomerToQuickBook(CustomerModal customerModal) throws FMSException;

	public void saveId(String id,String localCustomerId);
}
