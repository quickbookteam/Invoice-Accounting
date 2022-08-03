package com.accounting.service.Implimentation;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.fields;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accounting.entity.customer.LocalCustomer;
import com.accounting.exception.CustomException;
import com.accounting.exception.CustomerNotFoundException;
import com.accounting.modal.CommonResponse;
import com.accounting.modal.customer.CustomerModal;
import com.accounting.modal.customer.LocalCustomerModal;
import com.accounting.repositery.CustomerRepo;
import com.accounting.service.CustomerService;
import com.accounting.util.ChartHelper;
import com.accounting.util.Data;
import com.accounting.util.Helper;
import com.accounting.util.UtilConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;

@Service
@Qualifier("customerServiceImplementation")
public class CustomerServiceImpl implements CustomerService {

	public final CustomerRepo customerRepo;

	@Autowired
	private MongoTemplate mongoTemplate;

	private Helper helper;

	private ChartHelper chartHelper;

	private ModelMapper modelMapper;

	private ObjectMapper mapper;

	public CustomerServiceImpl(CustomerRepo customerRepo) {
		this.customerRepo = customerRepo;
		this.modelMapper = new ModelMapper();
		this.mapper = new ObjectMapper();
		this.helper = new Helper();
		this.chartHelper=new ChartHelper();
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
		throw new CustomerNotFoundException(UtilConstants.CUSTOMER_NOT_FOUND);
	}

	@Override
	public ResponseEntity<CommonResponse> getAll() {
		List<LocalCustomer> customerAll = customerRepo.findAll();
		List<LocalCustomerModal> customerModalList = new ArrayList<>();
		if (customerAll.size() < 1) {
			throw new CustomerNotFoundException(UtilConstants.CUSTOMER_NOT_FOUND);
		}
		for (LocalCustomer customer : customerAll) {
			LocalCustomerModal customerModal = modelMapper.map(customer, LocalCustomerModal.class);
			customerModalList.add(customerModal);
		}
		CommonResponse response = new CommonResponse(customerModalList, UtilConstants.CUSTOMER_LIST);
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
		throw new CustomerNotFoundException(UtilConstants.CUSTOMER_NOT_FOUND);
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
		throw new CustomerNotFoundException(UtilConstants.CUSTOMER_NOT_FOUND);
	}

	@Override
	public void saveId(String id, String localCustomerId) {
		LocalCustomer result = customerRepo.findById(localCustomerId).get();
		if (result != null) {
			result.setCustomerId(id);
			result.setStatus("Uploaded");
			customerRepo.save(result);
		} else {
			throw new CustomerNotFoundException("localcustomer not fond");
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
			throw new CustomerNotFoundException("localcustomer not fond");
		}
	}

	@Override
	public List<LocalCustomer> getCustomers_With_CreatedStatus() {
		List<LocalCustomer> localCustomerList = new ArrayList<>();
		Query query = new Query();
		query.addCriteria(Criteria.where("status").is("created"));
		localCustomerList = mongoTemplate.find(query, LocalCustomer.class);
		
		return localCustomerList;
	}

	@Override
	public List<LocalCustomer> getCustomers_With_UpdatedStatus() {
		List<LocalCustomer> localCustomerList = new ArrayList<>();
		Query query = new Query();
		query.addCriteria(Criteria.where("status").is("updated"));
		localCustomerList = mongoTemplate.find(query, LocalCustomer.class);
	
		return localCustomerList;
	}

	@Override
	public List<Data> customerCount() {
		DateTime endDate = new DateTime();
		DateTime startDate = new DateTime().minusMonths(2);
		final TypedAggregation<LocalCustomer> otpTypedAggregation = newAggregation(LocalCustomer.class,
				match(Criteria.where("createTime").gte(startDate).lte(endDate)),
				project("createTime").andExpression("dayOfMonth(createTime)").as("day")
						.andExpression("month(createTime)").as("month").andExpression("year(createTime)").as("year"),
				group(fields().and("month")).first("createTime").as("createTime").count().as("count"));
		List<Data> dataList = mongoTemplate.aggregate(otpTypedAggregation, Data.class).getMappedResults();
		return dataList;
	}

	@Override
	public ResponseEntity<String> generateCharts() {
		List<Data> list = customerCount();
		chartHelper.generatePieChart(list, "D:\\charts");
		return null;
	}

}
