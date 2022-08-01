package com.accounting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.accounting.modal.customer.LocalCustomerModal;
import com.accounting.util.Helper;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;


public interface CustomerCSVServices {
    
	  List<Customer> listAll() throws Exception;
	  ResponseEntity<LocalCustomerModal> addCustomersCsv(MultipartFile file);
    }
     
