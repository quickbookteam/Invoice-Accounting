package com.invoice_acounting.service.Implimentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.invoice_acounting.entity.customer.LocalCustomer;
import com.invoice_acounting.modal.customer.CustomerModal;
import com.invoice_acounting.modal.customer.LocalCustomerModal;
import com.invoice_acounting.repositery.CustomerRepo;
import com.invoice_acounting.service.CustomerService;
import com.invoice_acounting.util.Helper;

@Service("CustomerServiceImpl")
public class CustomerServiceImpl implements CustomerService {

	
	public final CustomerRepo customerRepo;


  
	
	@Autowired
	MongoTemplate mongoTemplate;

	Helper helper;

	ModelMapper modelMapper;

	ObjectMapper mapper;



	public CustomerServiceImpl(CustomerRepo customerRepo) {
       this.customerRepo=customerRepo;
		this.modelMapper = new ModelMapper();
		this.mapper = new ObjectMapper();
		this.helper = new Helper();
	}

	@Override
	public ResponseEntity<?> save(CustomerModal customerModal) {
		LocalCustomer localCustomer =modelMapper.map(customerModal, LocalCustomer.class);
		localCustomer.setStatus("created");
		localCustomer.setCustomerId("0");
		customerRepo.save(localCustomer);
		return new ResponseEntity<>(localCustomer, HttpStatus.OK);
	}

	@Override
	public LocalCustomerModal get(String id) {
		Optional<?> optionalCustomer = customerRepo.findById(id);
		if (!optionalCustomer.isEmpty()) {
			LocalCustomer customer = (LocalCustomer) optionalCustomer.get();
			LocalCustomerModal customerModal =modelMapper.map(customer, LocalCustomerModal.class);
			return customerModal;
		}
		return null;
	}

	@Override
	public ResponseEntity<?> delete(String id) {
		Optional<?> optionalCustomer = customerRepo.findById(id);
		LocalCustomer localCustomer = new LocalCustomer();
		localCustomer.set_id(id);
		if (!optionalCustomer.isEmpty()) {
			customerRepo.delete(localCustomer);
			return new ResponseEntity("Customer Deleted", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity("Customer not valid", HttpStatus.BAD_REQUEST);
	}

	@Override
	public List<LocalCustomerModal> getAll() {
		List<LocalCustomer> customerAll = customerRepo.findAll();
		List<LocalCustomerModal> customerModalList = new ArrayList<>();
		if (customerAll.size() < 1) {
			return null;
		}
		for (LocalCustomer customer : customerAll) {
			LocalCustomerModal customerModal = modelMapper.map(customer, LocalCustomerModal.class);
			customerModalList.add(customerModal);
		}
		return customerModalList;
	}

	@Override
	public CustomerModal update(CustomerModal customer) {
		LocalCustomer Customer = customerRepo.findByCustomerId(customer.getId());
		if (Customer != null) {

			LocalCustomer actualCustomer = modelMapper.map(customer, LocalCustomer.class);
			actualCustomer.setCustomerId(customer.getId());
			actualCustomer.set_id(Customer.get_id());
			actualCustomer.setStatus("updated");
			customerRepo.save(actualCustomer);
		}
		return customer;
	}

	@Override
	public LocalCustomerModal findById(String id) {
		LocalCustomer localCustomer = customerRepo.findById(id).get();
		LocalCustomerModal customerModal = modelMapper.map(localCustomer, LocalCustomerModal.class);
		return customerModal;
	}

	@Override
	public List<LocalCustomer> findAllLocalCustomers() {
		return customerRepo.findAll();
	}

	@Override
	public Customer saveCustomerToQuickBook(LocalCustomerModal customerModal) throws FMSException {
		DataService dataService = helper.getConnection();

		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		Customer customer = mapper.convertValue(customerModal, Customer.class);

		Customer customer1 = dataService.add(customer);
		return customer1;
	}

	@Override
	public void saveId(String id, String localCustomerId) {
		LocalCustomer result = customerRepo.findById(localCustomerId).get();
		result.setCustomerId(id);
		result.setStatus("Uploaded");
		customerRepo.save(result);
	}

	@Override
	public Customer updateCustomerToQuickBook(Customer customer) throws FMSException {
		DataService dataService = helper.getConnection();
		Customer customer1 = dataService.add(customer);
		return customer1;
	}

	@Override
	public void updateStatus(String customerId) {
		LocalCustomer result = customerRepo.findByCustomerId(customerId);
		result.setStatus("Uploaded");
		customerRepo.save(result);
	}

	@Override
	public List<LocalCustomer> getCustomers_With_CreatedStatus() {
		List<LocalCustomer> localCustomerList = new ArrayList<>();
		Query query = new Query();
		query.addCriteria(Criteria.where("status").is("created"));
		localCustomerList = mongoTemplate.find(query, LocalCustomer.class);
		System.out.println(localCustomerList);
		return localCustomerList;
	}

	@Override
	public List<LocalCustomer> getCustomers_With_UpdatedStatus() {
		List<LocalCustomer> localCustomerList = new ArrayList<>();
		Query query = new Query();
		query.addCriteria(Criteria.where("status").is("updated"));
		localCustomerList = mongoTemplate.find(query, LocalCustomer.class);
		System.out.println(localCustomerList);
		return localCustomerList;
	}

}
