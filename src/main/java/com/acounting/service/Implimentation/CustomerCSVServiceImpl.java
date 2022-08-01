package com.acounting.service.Implimentation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.acounting.entity.customer.LocalCustomer;
import com.acounting.modal.customer.LocalCustomerModal;
import com.acounting.repositery.CustomerRepo;
import com.acounting.service.CustomerCSVServices;
import com.acounting.util.CSVHelper;
import com.acounting.util.Helper;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;

@Service
public class CustomerCSVServiceImpl implements CustomerCSVServices {
	CustomerCSVServiceImpl() {
		this.modelMapper = new ModelMapper();
		this.helper = new Helper();
	}

	@Autowired
	private CustomerRepo customerRepo;

	private Helper helper;

	private ModelMapper modelMapper;

	@Autowired
	private CSVHelper cSVHelper;

	@Override
	public List<Customer> listAll() throws FMSException {
		DataService dataService = helper.getConnection();
		return dataService.findAll(new Customer());
	}

	@Override
	public ResponseEntity<LocalCustomerModal> addCustomersCsv(MultipartFile file) {

		List<LocalCustomerModal> customerModals = cSVHelper.fileToCustomer(file);
		List<LocalCustomer> customers = customerModals.stream()
				.map(customer -> modelMapper.map(customer, LocalCustomer.class)).collect(Collectors.toList());

		customerRepo.saveAll(customers);
		return new ResponseEntity<LocalCustomerModal>(HttpStatus.OK);
	}
}