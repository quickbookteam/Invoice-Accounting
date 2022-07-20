package com.invoice_acounting.controller;

import java.util.Date;
import java.util.List;

import com.invoice_acounting.dao.CustomerDao;
import com.invoice_acounting.entity.customer.LocalCustomer;
import org.apache.log4j.Logger;
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

import com.intuit.ipp.data.Customer;
import com.intuit.ipp.exception.FMSException;
import com.invoice_acounting.modal.customer.CustomerModal;
import com.invoice_acounting.repositery.CustomerRepo;
import com.invoice_acounting.service.Implimentation.CustomerCSVServiceImpl;
import com.invoice_acounting.service.Implimentation.CustomerServiceImpl;
import com.invoice_acounting.util.Helper;

@RestController
public class CustomerController {

	@Autowired
	CustomerRepo customerRepo;

	@Autowired
	CustomerServiceImpl customerService;

	@Autowired
	Helper helper;
	@Autowired
	CustomerDao customerDao;

	@Autowired
	CustomerCSVServiceImpl service;
	private static final Logger logger = Logger.getLogger(CustomerController.class);
	
	@GetMapping("/customer/{id}")
	public CustomerModal get(@PathVariable("id") String id) throws FMSException {
		return customerService.get(id);
	}
	
	@PostMapping("/customer")
	public ResponseEntity<?> addLocalCustomer(@RequestBody CustomerModal customer)throws Exception {
		customer.setLastUpdatedTime(new Date());
		customer.setCreateTime(new Date());
		return customerService.save(customer);
	}
	
	@DeleteMapping("/customer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") String id){
		return customerService.delete(id);
	}

	@GetMapping("/allcustomer")
	public List<CustomerModal> getAllCustomer() throws FMSException {
		return customerService.getAll();
	}
	
	@PutMapping("/customer/{id}")
	public CustomerModal updateCustomer(@PathVariable("id") String id, @RequestBody CustomerModal customer) {
		return customerService.update(id,customer);
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
	@PostMapping("/customers")
	public ResponseEntity<CustomerModal> addCustomersCsv(MultipartFile file) {
		return service.addCustomersCsv(file);
	}

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
