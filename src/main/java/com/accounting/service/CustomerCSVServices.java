package com.accounting.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.accounting.modal.customer.LocalCustomerModal;
import com.intuit.ipp.data.Customer;

public interface CustomerCSVServices {

	List<Customer> listAll() throws Exception;

	ResponseEntity<LocalCustomerModal> addCustomersCsv(MultipartFile file);
}
