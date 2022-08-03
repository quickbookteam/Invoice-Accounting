package com.accounting.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.accounting.entity.customer.LocalCustomer;
import com.accounting.modal.CommonResponse;
import com.accounting.modal.Data;
import com.accounting.modal.customer.CustomerModal;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.exception.FMSException;

public interface CustomerService {

	ResponseEntity<CommonResponse> save(CustomerModal customerModal);

	ResponseEntity<CommonResponse> getCustomerById(String id) throws Exception;

	ResponseEntity<CommonResponse> delete(String id);

	ResponseEntity<CommonResponse> getAll();

	ResponseEntity<CommonResponse> update(CustomerModal customer);

	void saveCustomerId(String id, String localCustomerId);

	List<LocalCustomer> getCustomersWithCreatedStatus();

	List<LocalCustomer> getCustomersWithUpdatedStatus();

	List<Data> customerCount();

	ResponseEntity<String> generateCharts();

}
