package com.invoice_acounting.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.intuit.ipp.data.Customer;
import com.intuit.ipp.exception.FMSException;
import com.invoice_acounting.entity.customer.LocalCustomer;
import com.invoice_acounting.modal.CommonResponse;
import com.invoice_acounting.modal.customer.CustomerModal;
import com.invoice_acounting.util.Data;


public interface CustomerService {

	ResponseEntity<CommonResponse> save(CustomerModal customerModal);

	ResponseEntity<CommonResponse> getCustomerById(String id)throws Exception;

	ResponseEntity<CommonResponse> delete(String id);

	ResponseEntity<CommonResponse> getAll();

	ResponseEntity<CommonResponse> update(CustomerModal customer);

//	public ResponseEntity<LocalCustomerModal> findById(String id);



	
	public Customer updateCustomerToQuickBook(Customer customer) throws FMSException;

	public void saveId(String id, String localCustomerId);

	public void updateStatus(String customerId);

	List<LocalCustomer> getCustomers_With_CreatedStatus();

	List<LocalCustomer> getCustomers_With_UpdatedStatus();
	public List<Data> customerCount();
	
	  public ResponseEntity<String> generateCharts() ;

}
