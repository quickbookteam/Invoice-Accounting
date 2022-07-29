package com.invoice_acounting.service.Implimentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.invoice_acounting.entity.customer.LocalCustomer;
import com.invoice_acounting.exception.CustomException;
import com.invoice_acounting.exception.CustomerNotFound;
import com.invoice_acounting.modal.CommonResponse;
import com.invoice_acounting.modal.customer.CustomerModal;
import com.invoice_acounting.modal.customer.LocalCustomerModal;
import com.invoice_acounting.repositery.CustomerRepo;
import com.invoice_acounting.service.CustomerService;
import com.invoice_acounting.util.Helper;
import com.invoice_acounting.util.UtilConstants;

@Service
@Qualifier("customerServiceImplementation")
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepo customerRepo;

	@Autowired
	private MongoTemplate mongoTemplate;

	private Helper helper;

	private ModelMapper modelMapper;

	private ObjectMapper mapper;

	public CustomerServiceImpl(CustomerRepo customerRepo) {
		this.customerRepo = customerRepo;
		this.modelMapper = new ModelMapper();
		this.mapper = new ObjectMapper();
		this.helper = new Helper();

	}

	@Override
	public ResponseEntity<CommonResponse> save(CustomerModal customerModal) {
		try {
			LocalCustomer localCustomer = modelMapper.map(customerModal, LocalCustomer.class);
			localCustomer.setStatus("created");
			localCustomer.setCustomerId("0");
			customerRepo.save(localCustomer);
			CommonResponse response = new CommonResponse(localCustomer, UtilConstants.CUSTOMER_SAVED);
			return new ResponseEntity<CommonResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<CommonResponse> delete(String id) {
		Optional<?> optionalCustomer = customerRepo.findById(id);
		LocalCustomer localCustomer = new LocalCustomer();
		localCustomer.set_id(id);
		if (!optionalCustomer.isEmpty()) {
			customerRepo.delete(localCustomer);
			CommonResponse response = new CommonResponse(null, UtilConstants.CUSTOMER_DELETED);
			return new ResponseEntity<CommonResponse>(response, HttpStatus.OK);
		}
		throw new CustomerNotFound(UtilConstants.CUSTOMER_NOT_FOUND);
	}

	@Override
	public ResponseEntity<CommonResponse> getAll() {
		List<LocalCustomer> customerAll = customerRepo.findAll();
		List<LocalCustomerModal> customerModalList = new ArrayList<>();
		if (customerAll.size() < 1) {
			throw new CustomerNotFound(UtilConstants.CUSTOMER_NOT_FOUND);
		}
		for (LocalCustomer customer : customerAll) {
			LocalCustomerModal customerModal = modelMapper.map(customer, LocalCustomerModal.class);
			customerModalList.add(customerModal);
		}
		CommonResponse response = new CommonResponse(customerModalList,UtilConstants.CUSTOMER_LIST);
		return new ResponseEntity<CommonResponse>(response, HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<CommonResponse> update(CustomerModal customer) {
		LocalCustomer Customer = customerRepo.findByCustomerId(customer.getId());
		if (Customer != null) {

			LocalCustomer actualCustomer = modelMapper.map(customer, LocalCustomer.class);
			actualCustomer.setCustomerId(customer.getId());
			actualCustomer.set_id(Customer.get_id());
			actualCustomer.setStatus("updated");
			customerRepo.save(actualCustomer);

			CommonResponse response = new CommonResponse(customer, UtilConstants.CUSTOMER_UPDATED);
			return new ResponseEntity<CommonResponse>(response, HttpStatus.ACCEPTED);
		}
		throw new CustomerNotFound(UtilConstants.CUSTOMER_NOT_FOUND);
	}

	@Override
	public ResponseEntity<CommonResponse> getCustomerById(String id) {
		Optional<?> optionalCustomer = customerRepo.findById(id);
		if (!optionalCustomer.isEmpty()) {
			LocalCustomer customer = (LocalCustomer) optionalCustomer.get();
			LocalCustomerModal customerModal = modelMapper.map(customer, LocalCustomerModal.class);
			CommonResponse response = new CommonResponse(customerModal, UtilConstants.CUSTOMER_FOUND);
			return new ResponseEntity<CommonResponse>(response, HttpStatus.FOUND);
			
		}
		throw new CustomerNotFound(UtilConstants.CUSTOMER_NOT_FOUND);
	}



	@Override
	public void saveId(String id, String localCustomerId) {
		LocalCustomer result = customerRepo.findById(localCustomerId).get();
		if (result != null) {
			result.setCustomerId(id);
			result.setStatus("Uploaded");
			customerRepo.save(result);
		} else {
//			throw new CustomerException("localcustomer not fond");
		}
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
		if (result != null) {
			result.setStatus("Uploaded");
			customerRepo.save(result);
		} else {
//			throw new CustomerException("localcustomer not fond");
		}
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
