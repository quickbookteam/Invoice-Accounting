package com.accounting.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.accounting.entity.customer.LocalCustomer;
import com.accounting.modal.CommonResponse;
import com.accounting.modal.customer.CustomerModal;
import com.accounting.util.Data;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.exception.FMSException;


public interface CustomerService {

	ResponseEntity<CommonResponse> save(CustomerModal customerModal);

	ResponseEntity<CommonResponse> getCustomerById(String id)throws Exception;

	ResponseEntity<CommonResponse> delete(String id);

	ResponseEntity<CommonResponse> getAll();

	ResponseEntity<CommonResponse> update(CustomerModal customer);

//	public ResponseEntity<LocalCustomerModal> findById(String id);



	
	 Customer updateCustomerToQuickBook(Customer customer) throws FMSException;

	 void saveId(String id, String localCustomerId);

	 void updateStatus(String customerId);

	List<LocalCustomer> getCustomers_With_CreatedStatus();

	List<LocalCustomer> getCustomers_With_UpdatedStatus();
	 List<Data> customerCount();
	
	  ResponseEntity<String> generateCharts() ;

}
