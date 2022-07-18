package com.invoice_acounting.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.ipp.data.CompanyInfo;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.data.Invoice;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.services.QueryResult;
import com.invoice_acounting.config.QuickBookIntegration;
import com.invoice_acounting.repositery.CustomerRepo;
import com.invoice_acounting.service.CustomerCSVServices;
import com.invoice_acounting.service.Implimentation.CustomerServiceImpl;
import com.invoice_acounting.util.Helper;
import org.modelmapper.ModelMapper;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@RestController
public class CustomerController {

	@Autowired
	CustomerRepo customerRepo;

	@Autowired
	CustomerServiceImpl customerService;

	@Autowired
	Helper helper;
	
	@Autowired
	CustomerCSVServices service;
	private static final Logger logger = Logger.getLogger(CustomerController.class);

	@PostMapping("/customer")
	public ResponseEntity<Customer> addInvoice(@RequestBody Customer customer) throws Exception {
		DataService dataService = helper.getConnection();
		System.out.println("DataService =" + dataService);
		Customer result = dataService.add(customer);
		return new ResponseEntity<Customer>(result, HttpStatus.OK);
	}

	@PostMapping("/customer2")
	public ResponseEntity<?> addCus(@RequestBody com.invoice_acounting.entity.customer.Customer customer)
			throws Exception {

//        DataService dataService=helper.getConnection();
//        System.out.println("DataService ="+dataService);
//       Customer result= dataService.add(customer);
		customerService.save(customer);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	// Deletion can not be perform
	@DeleteMapping("/customer")
	public ResponseEntity<Customer> delete(@RequestBody Customer customer) throws Exception {
		DataService dataService = helper.getConnection();
		Customer result = dataService.delete(customer);
		return new ResponseEntity<Customer>(result, HttpStatus.OK);
	}

	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> getCustomer() throws Exception {
		String sql = "select * from customer";
		QueryResult queryResult;
		try {
			queryResult = helper.getConnection().executeQuery(sql);
			List<Customer> customerInfo = (List<Customer>) queryResult.getEntities();
			ObjectMapper mapper = new ObjectMapper();
//            System.out.println(mapper.writeValueAsString(customerInfo));
			return new ResponseEntity<List<Customer>>(customerInfo, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/customer/{id}")
	public Customer getCustomerById(@PathVariable("id") String id) throws Exception {
		DataService dataService = helper.getConnection();
		Customer customer = new Customer();
		customer.setId(id);

		Customer customerInfo = dataService.findById(customer);
		if (!customerInfo.equals(null)) {
			return customerInfo;
		}
		return null;
	}

	@PutMapping("/customer/{id}")
	public Customer updateCustomer(@PathVariable("id") String id, @RequestBody Customer customer) throws Exception {
		DataService dataService = helper.getConnection();
		Customer customer1 = new Customer();
		customer1.setId(id);
		Customer customerInfo = dataService.findById(customer1);
		if (customerInfo.equals(null)) {
			return null;
		}
		dataService.update(customer);
		return customer;
	}

	@GetMapping("/allcustomer")
	public Customer getById() throws FMSException {
		com.invoice_acounting.entity.customer.Customer customer = customerRepo.findById("62d50e8562939a48523c545d")
				.get();
		ModelMapper mapper = new ModelMapper();
		Customer c =new Customer();
		Customer customermap = mapper.map(customer, Customer.class);
		System.out.println("customer " + customermap);
		DataService dataService = helper.getConnection();
		System.out.println("DataService =" + dataService);
		Customer result = dataService.add(customermap);
		return result;
	}

	@GetMapping("/customer/export")
    public void exportToCSV(HttpServletResponse response) throws IOException, FMSException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
       // String csvFileName = "customer.csv";
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=customers_" + currentDateTime + ".csv";
        //String headerValue = csvFileName;
        response.setHeader(headerKey, headerValue);
         
        List<Customer> listUsers = service.listAll();
 
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"User ID","name"};
        String[] nameMapping = {"id", "givenName"};
         
        csvWriter.writeHeader(csvHeader);
     
        for (Customer user : listUsers) {
            csvWriter.write(user, nameMapping);
        }
       
         
        csvWriter.close();
         
    }
}
