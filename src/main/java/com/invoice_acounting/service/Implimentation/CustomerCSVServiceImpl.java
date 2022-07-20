package com.invoice_acounting.service.Implimentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.intuit.ipp.data.Customer;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.invoice_acounting.dao.CustomerDao;
import com.invoice_acounting.modal.customer.CustomerModal;
import com.invoice_acounting.service.CustomerCSVServices;
import com.invoice_acounting.util.Helper;
@Service
public class CustomerCSVServiceImpl implements CustomerCSVServices {
	 @Autowired
	    Helper helper;
	 @Autowired
	 CustomerDao dao;
	     
	    public List<Customer> listAll() throws FMSException {
	        DataService dataService = helper.getConnection();
	        return dataService.findAll(new Customer());
}


		@Override
		public ResponseEntity<CustomerModal> addCustomersCsv(MultipartFile file) {
			
			return dao.addCustomersCsv(file);
		}
}