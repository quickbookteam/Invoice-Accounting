package com.invoice_acounting.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.data.Invoice;
import com.intuit.ipp.services.DataService;
import com.invoice_acounting.config.QuickBookIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class CustomerController {
    @Autowired
    QuickBookIntegration quickBookIntegration;

    DataService dataService;

    @GetMapping("/")
    public String demo()
    {
        return "hello";
    }
    @RequestMapping("/addCustomer")
    public Customer addInvoice(@RequestBody Customer customer) throws Exception
    {
        DataService dataService=quickBookIntegration.demo();
        System.out.println("DataService ="+dataService);
        dataService.add(customer);
        return customer;
    }


}
