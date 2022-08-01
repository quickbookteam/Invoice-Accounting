package com.acounting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.acounting.modal.customer.LocalCustomerModal;
import com.acounting.util.Helper;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;


public interface CustomerCSVServices {
    
	 public List<Customer> listAll() throws Exception;
	 public ResponseEntity<LocalCustomerModal> addCustomersCsv(MultipartFile file);
    }
     
