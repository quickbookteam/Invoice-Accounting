package com.invoice_acounting.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.ipp.data.CompanyInfo;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.data.Invoice;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.services.QueryResult;
import com.invoice_acounting.config.QuickBookIntegration;
import com.invoice_acounting.repositery.CustomerRepo;
import com.invoice_acounting.service.Implimentation.CustomerServiceImpl;
import com.invoice_acounting.util.Helper;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerRepo customerRepo; 
    
	@Autowired
	CustomerServiceImpl customerService;
	
    @Autowired
    Helper helper;
    private static final Logger logger = Logger.getLogger(CustomerController.class);
    
    @PostMapping("/customer")
    public ResponseEntity<Customer> addInvoice(@RequestBody Customer customer) throws Exception
    {
        DataService dataService=helper.getConnection();
        System.out.println("DataService ="+dataService);
       Customer result= dataService.add(customer);
        return new ResponseEntity<Customer>(result, HttpStatus.OK);
    }
    
    @PostMapping("/customer2")
    public ResponseEntity<?> addCus(@RequestBody com.invoice_acounting.entity.customer.Customer customer) throws Exception
    {
    	
//        DataService dataService=helper.getConnection();
//        System.out.println("DataService ="+dataService);
//       Customer result= dataService.add(customer);
    	customerService.save(customer);
    	
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    //Deletion can not be perform
    @DeleteMapping("/customer")
    public  ResponseEntity<Customer> delete(@RequestBody Customer customer) throws Exception {
        DataService dataService = helper.getConnection();
        Customer result=  dataService.delete(customer);
        return new ResponseEntity<Customer>(result, HttpStatus.OK);
    }

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> getCustomer() throws Exception
    {
        String sql = "select * from customer";
        QueryResult queryResult;
        try {
            queryResult = helper.getConnection().executeQuery(sql);
            List<Customer> customerInfo= (List<Customer>) queryResult.getEntities();
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
    public Customer getCustomerById(@PathVariable("id") String id) throws Exception
    {
        DataService dataService = helper.getConnection();
        Customer customer = new Customer();
        customer.setId(id);

        Customer customerInfo = dataService.findById(customer);
        if(!customerInfo.equals(null)) {
            return customerInfo;
        }
        return null;
    }
    
    @PutMapping("/customer/{id}")
    public Customer updateCustomer(@PathVariable("id") String id,@RequestBody Customer customer) throws Exception
    {
        DataService dataService = helper.getConnection();
        Customer customer1 =new Customer();
        customer1.setId(id);
        Customer customerInfo = dataService.findById(customer1);
        if(customerInfo.equals(null)) {
            return null;
        }
        dataService.update(customer);
        return customer;
    }

    
    
    @GetMapping("/allcustomer")
    public com.invoice_acounting.entity.customer.Customer getById()
    {
    	return customerRepo.findById("62d4fd07fff56f3a540d42a8").get();
    }
    
    
}
