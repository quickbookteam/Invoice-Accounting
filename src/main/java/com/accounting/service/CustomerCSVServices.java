package com.accounting.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.accounting.modal.customer.LocalCustomerModal;
import com.intuit.ipp.data.Customer;

public interface CustomerCSVServices {


	ResponseEntity<LocalCustomerModal> addCustomersCsv(MultipartFile file);
}
