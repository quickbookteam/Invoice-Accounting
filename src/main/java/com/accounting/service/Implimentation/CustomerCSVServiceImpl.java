package com.accounting.service.Implimentation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.accounting.entity.customer.LocalCustomer;
import com.accounting.exception.CustomFileNotFoundException;
import com.accounting.modal.customer.LocalCustomerModal;
import com.accounting.repositery.CustomerRepo;
import com.accounting.service.CustomerCSVServices;
import com.accounting.util.CSVHelper;
import com.accounting.util.UtilConstants;

@Service
public class CustomerCSVServiceImpl implements CustomerCSVServices {
	
	private CustomerRepo customerRepo;

	private ModelMapper modelMapper;

	private CSVHelper cSVHelper;
	
	@Autowired
	public CustomerCSVServiceImpl(CustomerRepo customerRepo, CSVHelper cSVHelper) {
		this.modelMapper = new ModelMapper();
		this.customerRepo = customerRepo;
		this.cSVHelper = cSVHelper;

	}

	

	@Override
	public ResponseEntity<LocalCustomerModal> addCustomersCsv(MultipartFile file) {
      if(file.isEmpty())
    	  throw new CustomFileNotFoundException(UtilConstants.FILE_NOT_FOUND);
		List<LocalCustomerModal> customerModals = cSVHelper.fileToCustomer(file);
		List<LocalCustomer> customers = customerModals.stream()
				.map(customer -> modelMapper.map(customer, LocalCustomer.class)).collect(Collectors.toList());

		customerRepo.saveAll(customers);
		return new ResponseEntity<LocalCustomerModal>(HttpStatus.OK);
	}
}