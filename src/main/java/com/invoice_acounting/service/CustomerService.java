package com.invoice_acounting.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.intuit.ipp.data.Customer;
import com.intuit.ipp.exception.FMSException;
import com.invoice_acounting.entity.customer.LocalCustomer;
import com.invoice_acounting.modal.customer.CustomerModal;
import com.invoice_acounting.modal.customer.LocalCustomerModal;

@Service
public interface CustomerService {
	
	ResponseEntity<?> save(CustomerModal customerModal);

	LocalCustomerModal get(String id);
	
	public ResponseEntity<?> delete(String id);
	
	public List<LocalCustomerModal> getAll();
	
	public CustomerModal update(CustomerModal customer);

	public LocalCustomerModal findById(String id);
	public List<LocalCustomer> findAllLocalCustomers();

	public Customer saveCustomerToQuickBook(LocalCustomerModal customerModal) throws FMSException;

	public Customer updateCustomerToQuickBook(Customer customer) throws FMSException;

	public void saveId(String id,String localCustomerId);
	public void updateStatus(String customerId) ;

	List<LocalCustomer> getCustomers_With_CreatedStatus();

	List<LocalCustomer> getCustomers_With_UpdatedStatus();
}
