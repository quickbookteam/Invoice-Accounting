package com.invoice_acounting.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.invoice_acounting.entity.customer.LocalCustomer;
import com.invoice_acounting.modal.customer.CustomerModal;
import com.invoice_acounting.repositery.CustomerRepo;
import com.invoice_acounting.util.CSVHelper;
import com.invoice_acounting.util.Helper;

@Component
public class CustomerDao {

	@Autowired
	CustomerRepo customerRepo;

	@Autowired
	Helper helper;
	
	@Autowired
	CSVHelper cSVHelper;

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	ModelMapper modelMapper;

	

	public ResponseEntity<?> save(CustomerModal customer) {
		LocalCustomer localCustomer = helper.getMapper().map(customer, LocalCustomer.class);
		localCustomer.setStatus("created");
		localCustomer.setCustomerId("0");
		customerRepo.save(localCustomer);
		return new ResponseEntity<>(localCustomer, HttpStatus.OK);

	}

	public CustomerModal get(String id) {
		Optional<?> optionalCustomer = customerRepo.findById(id);
		if (!optionalCustomer.isEmpty()) {
			LocalCustomer customer = (LocalCustomer) optionalCustomer.get();
			CustomerModal customerModal = helper.getMapper().map(customer, CustomerModal.class);
			return customerModal;
		}
		return null;
	}

	public ResponseEntity<?> delete(String id) {
		Optional<?> optionalCustomer = customerRepo.findById(id);
		LocalCustomer localCustomer = new LocalCustomer();
		localCustomer.set_id(id);
		if (!optionalCustomer.isEmpty()) {
			customerRepo.delete(localCustomer);
		}
		return new ResponseEntity("Customer not valid", HttpStatus.BAD_REQUEST);
	}

	public List<CustomerModal> getAll() {
		List<LocalCustomer> customerAll = customerRepo.findAll();
		List<CustomerModal> customerModalList = new ArrayList<>();
		if (customerAll.size() < 1) {
			return null;
		}
		for (LocalCustomer customer : customerAll) {
			CustomerModal customerModal = helper.getMapper().map(customer, CustomerModal.class);
			customerModalList.add(customerModal);
		}
		return customerModalList;
	}

	public CustomerModal update(String id, CustomerModal customer) {
		if (customerRepo.existsById(id)) {
			customerRepo.save(helper.getMapper().map(customer, LocalCustomer.class));
		}
		return customer;
	}

	public ResponseEntity<CustomerModal> addCustomersCsv(MultipartFile file) {

		List<CustomerModal> customerModals = cSVHelper.fileToCustomer(file);
		List<LocalCustomer> customers = customerModals.stream()
				.map(customer -> modelMapper.map(customer, LocalCustomer.class)).collect(Collectors.toList());

		customerRepo.saveAll(customers);
		return new ResponseEntity<CustomerModal>(HttpStatus.OK);

	}
	public CustomerModal findById(String id)
	{
LocalCustomer localCustomer=customerRepo.findById(id).get();
		CustomerModal customerModal = helper.getMapper().map(localCustomer, CustomerModal.class);
		return customerModal;
	}
	public List<LocalCustomer> findAllLocalCustomers()
	{
		return customerRepo.findAll();
	}
	public Customer saveCustomerToQuickBook(CustomerModal customerModal) throws FMSException {
		DataService dataService=helper.getConnection();
		ObjectMapper mapper=new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		Customer customer= mapper.convertValue(customerModal, Customer.class);


Customer customer1=dataService.add(customer);
return customer1;


	}
public void saveID(String id,String localCustomerID)
{
	 LocalCustomer result=customerRepo.findById(localCustomerID).get();
	result.setCustomerId(id);
	result.setStatus("Uploaded");
	customerRepo.save(result);
}
public List<LocalCustomer> getCustomers_With_CreatedStatus()
{
	List<LocalCustomer> localCustomerList =new ArrayList<>();
	Query query =new Query();
	query.addCriteria(Criteria.where("status").is("created"));
	localCustomerList = mongoTemplate.find(query,LocalCustomer.class);
	System.out.println(localCustomerList);
	return localCustomerList;
}
}
