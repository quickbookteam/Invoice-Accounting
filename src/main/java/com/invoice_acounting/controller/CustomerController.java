package com.invoice_acounting.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.ipp.data.CompanyInfo;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.data.Invoice;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.services.QueryResult;
import com.invoice_acounting.config.QuickBookIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.math.BigDecimal;

@RestController
public class CustomerController {
    @Autowired
    QuickBookIntegration quickBookIntegration;

    DataService dataService;

    @RequestMapping("/addCustomer")
    public Customer addCustomer(@RequestBody Customer customer) throws Exception
    {
        DataService dataService=quickBookIntegration.demo();
        System.out.println("DataService ="+dataService);
        dataService.add(customer);
        return customer;
    }

    @RequestMapping("/addBulkCustomer")
    public String addBulkCustomer(@RequestBody File file) throws Exception
    {
    	ObjectMapper om = new ObjectMapper();
        Customer  customer= om.readValue(new File("test.json.xlsx"), Customer.class);
        DataService dataService=quickBookIntegration.demo();
//        dataService.upload(Customer.class,customer);
        return "added customer";
    }
    
    @GetMapping("/getCustomer")
    public void getCustomer() throws Exception
    {
    	String sql = "select * from customer";
        QueryResult queryResult;
		try {
			queryResult = quickBookIntegration.demo().executeQuery(sql);
			Customer customerInfo = (Customer) queryResult.getEntities();
			ObjectMapper mapper = new ObjectMapper();
			System.out.println(mapper.writeValueAsString(customerInfo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
