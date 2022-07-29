package com.invoice_acounting.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.intuit.ipp.exception.FMSException;
import com.invoice_acounting.modal.CommonResponse;
import com.invoice_acounting.modal.customer.CustomerModal;
import com.invoice_acounting.modal.customer.LocalCustomerModal;
import com.invoice_acounting.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CustomerController {

	
	CustomerService customerService;

	@Autowired
	CustomerController(CustomerService customerService)
	{
		 this.customerService=customerService;
	}


	@GetMapping("/customer/{id}")
	public  ResponseEntity<CommonResponse> get(@PathVariable("id") String id) throws Exception  {
	log.info("inside customer search by id");
				return customerService.getCustomerById(id);
			
		
	}

	@PostMapping("/customer")
	public  ResponseEntity<CommonResponse> addLocalCustomer(@RequestBody CustomerModal customer) throws Exception {
		log.info("inside add customer");
		log.info("customer details", customer);
		customer.setLastUpdatedTime(new Date());
		customer.setCreateTime(new Date());
		
		return customerService.save(customer);
	}

	@DeleteMapping("/customer/{id}")
	public  ResponseEntity<CommonResponse> deleteCustomer(@PathVariable("id") String id) {
		log.info("inside delete by id", id);
		return customerService.delete(id);
	}

	@GetMapping("/allcustomer")
	public  ResponseEntity<CommonResponse> getAllCustomer() throws FMSException {
		log.info("inside get all customer list");
		return customerService.getAll();
	}

	@PutMapping("/customer")
	public  ResponseEntity<CommonResponse> updateCustomer(@RequestBody CustomerModal customer) {
		log.info("inside update customer", customer);
		return customerService.update(customer);
	}


}
