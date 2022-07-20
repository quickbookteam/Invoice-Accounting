package com.invoice_acounting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.intuit.ipp.data.Customer;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.invoice_acounting.modal.customer.CustomerModal;
import com.invoice_acounting.util.Helper;


public interface CustomerCSVServices {
    
	 public List<Customer> listAll() throws Exception;
	 public ResponseEntity<CustomerModal> addCustomersCsv(MultipartFile file);
    }
     
