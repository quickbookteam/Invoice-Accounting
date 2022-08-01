package com.accounting.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.accounting.entity.customer.LocalCustomer;
import com.accounting.modal.CommonResponse;
import com.accounting.modal.customer.CustomerModal;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.exception.FMSException;


public interface CustomerService {

	ResponseEntity<CommonResponse> save(CustomerModal customerModal);

	ResponseEntity<CommonResponse> getCustomerById(String id)throws Exception;

	public ResponseEntity<CommonResponse> delete(String id);

	public  ResponseEntity<CommonResponse> getAll();

	public  ResponseEntity<CommonResponse> update(CustomerModal customer);

//	public ResponseEntity<LocalCustomerModal> findById(String id);



	
	public Customer updateCustomerToQuickBook(Customer customer) throws FMSException;

	public void saveId(String id, String localCustomerId);

	public void updateStatus(String customerId);

	List<LocalCustomer> getCustomers_With_CreatedStatus();

	List<LocalCustomer> getCustomers_With_UpdatedStatus();

	


}
