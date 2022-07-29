package com.invoice_acounting.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.invoice_acounting.modal.customer.CustomerModal;
import com.invoice_acounting.modal.customer.LocalCustomerModal;
import com.invoice_acounting.repositery.CustomerRepo;
import com.invoice_acounting.service.CustomerCSVServices;
import com.invoice_acounting.service.CustomerService;
import com.invoice_acounting.util.ChartHelper;
import com.invoice_acounting.util.Data;
import com.invoice_acounting.util.Helper;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@Autowired
	CustomerCSVServices service;
	
	ChartHelper chartHelper;

	@GetMapping("/customer/{id}")
	public ResponseEntity<LocalCustomerModal> get(@PathVariable("id") String id) throws Exception  {
	log.info("inside customer search by id");
				return customerService.getCustomerById(id);
			
		
	}

	@PostMapping("/customer")
	public ResponseEntity<?> addLocalCustomer(@RequestBody CustomerModal customer) throws Exception {
		log.info("inside add customer");
		log.info("customer details", customer);
		customer.setLastUpdatedTime(new Date());
		customer.setCreateTime(new Date());
		
		return customerService.save(customer);
	}

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") String id) {
		log.info("inside delete by id", id);
		return customerService.delete(id);
	}

	@GetMapping("/allcustomer")
	public List<LocalCustomerModal> getAllCustomer() throws FMSException {
		log.info("inside get all customer list");
		return customerService.getAll();
	}

	@PutMapping("/customer")
	public ResponseEntity<CustomerModal> updateCustomer(@RequestBody CustomerModal customer) {
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
        //	@GetMapping("/customer")
//	public ResponseEntity<List<Customer>> getCustomer() throws Exception {
//		String sql = "select * from customer";
//		QueryResult queryResult;
//		try {
//			queryResult = helper.getConnection().executeQuery(sql);
//			List<Customer> customerInfo = (List<Customer>) queryResult.getEntities();
//			ObjectMapper mapper = new ObjectMapper();
////            System.out.println(mapper.writeValueAsString(customerInfo));
//			return new ResponseEntity<List<Customer>>(customerInfo, HttpStatus.OK);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}

//	@PutMapping("/customer/{id}")
//	public Customer updateCustomer(@PathVariable("id") String id, @RequestBody Customer customer) throws Exception {
//		DataService dataService = helper.getConnection();
//		Customer customer1 = new Customer();
//		customer1.setId(id);
//		Customer customerInfo = dataService.findById(customer1);
//		if (customerInfo.equals(null)) {
//			return null;
//		}
//		dataService.update(customer);
//		return customer;
//	}

//	@GetMapping("/customer/{id}")
//	public Customer getCustomerById(@PathVariable("id") String id) throws Exception {
//		DataService dataService = helper.getConnection();
//		Customer customer = new Customer();
//		customer.setId(id);
//
//		Customer customerInfo = dataService.findById(customer);
//		if (!customerInfo.equals(null)) {
//			return customerInfo;
//		}
//		return null;
//	}

//	LocalCustomer customer = customerRepo.findById(id).get();
//	Customer customermap = helper.getMapper().map(customer, Customer.class);
//	System.out.println("customer " + customermap);
//	DataService dataService = helper.getConnection();
//	System.out.println("DataService =" + dataService);
//	Customer result = dataService.add(customermap);
//	return result;

//	@PostMapping("/customer10")
//	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) throws Exception {
//		DataService dataService = helper.getConnection();
//		System.out.println("DataService =" + dataService);
//		Customer result = dataService.add(customer);
//		System.out.println(result.getId());
//		return new ResponseEntity<Customer>(result, HttpStatus.OK);
//	}

//	// Deletion can not be perform
//	@DeleteMapping("/customer")
//	public ResponseEntity<Customer> delete(@RequestBody Customer customer) throws Exception {
//		DataService dataService = helper.getConnection();
//		Customer result = dataService.delete(customer);
//		return new ResponseEntity<Customer>(result, HttpStatus.OK);
//	}

//@PostMapping("/upload_customer")
//	public Customer saveCustomerToQuickBookServer() throws FMSException {
//	System.out.println(new Date());
//	CustomerModal customerModal=customerService.findById("62d7ce6624157643aaddc3dd");
//	Customer customer=customerService.saveCustomerToQuickBook(customerModal);
//
//
//
//	customerService.saveId(customer.getId(),"62d7ce6624157643aaddc3dd");
//	return customer;
//	}

//	@GetMapping("/list")
//	public List<LocalCustomer> list()
//	{
//		return customerDao.getCustomers_With_CreatedStatus();
//	}
}
