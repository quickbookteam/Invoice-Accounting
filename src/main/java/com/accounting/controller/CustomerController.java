package com.accounting.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.accounting.modal.CommonResponse;
import com.accounting.modal.customer.CustomerModal;
import com.accounting.modal.customer.LocalCustomerModal;
import com.accounting.service.CustomerCSVServices;
import com.accounting.service.CustomerService;
import com.intuit.ipp.exception.FMSException;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CustomerController {

	private CustomerService customerService;

	private CustomerCSVServices service;

	@Autowired
	public CustomerController(CustomerService customerService, CustomerCSVServices service) {
		this.customerService = customerService;
		this.service = service;
	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<CommonResponse> get(@PathVariable("id") String id) throws Exception {
		log.info("inside customer search by id");
		return customerService.getCustomerById(id);

	}

	@PostMapping("/customer")
	public ResponseEntity<CommonResponse> addLocalCustomer(@Valid @RequestBody CustomerModal customer) throws MethodArgumentNotValidException {
		log.info("inside add customer");
		log.info("customer details", customer);
		customer.setLastUpdatedTime(new Date());
		customer.setCreateTime(new Date());

		return customerService.save(customer);
	}

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<CommonResponse> deleteCustomer(@PathVariable("id") String id) {
		log.info("inside delete by id", id);
		return customerService.delete(id);
	}

	@GetMapping("/allcustomer")
	public ResponseEntity<CommonResponse> getAllCustomer() throws FMSException {
		log.info("inside get all customer list");
		return customerService.getAll();
	}

	@PutMapping("/customer")
	public ResponseEntity<CommonResponse> updateCustomer(@RequestBody CustomerModal customer) {
		log.info("inside update customer", customer);
		return customerService.update(customer);
	}

	@PostMapping("/customers")
	public ResponseEntity<LocalCustomerModal> addCustomersCsv(MultipartFile file) {
		log.info("inside add customer using csv file");
		return service.addCustomersCsv(file);
	}

	@GetMapping("/charts")
	public ResponseEntity<String> generateCharts() {
		log.info("inside chart preparation");
		return customerService.generateCharts();
	}
}
