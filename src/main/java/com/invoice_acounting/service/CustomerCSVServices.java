package com.invoice_acounting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.intuit.ipp.data.Customer;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.invoice_acounting.util.Helper;

@Service
public class CustomerCSVServices {
    
    @Autowired
    Helper helper;
    
     
    public List<Customer> listAll() throws FMSException {
        DataService dataService = helper.getConnection();
        return dataService.findAll(new Customer());
    }
     
}