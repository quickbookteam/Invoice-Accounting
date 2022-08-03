package com.accounting.service.Implimentation;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.fields;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.accounting.connection.Connections;
import com.accounting.entity.customer.LocalCustomer;
import com.accounting.entity.invoice.LocalInvoice;
import com.accounting.exception.CustomException;
import com.accounting.modal.Data;
import com.accounting.modal.customer.LocalCustomerModal;
import com.accounting.modal.invoice.InvoiceModal;
import com.accounting.repositery.CustomerRepo;
import com.accounting.repositery.InvoiceRepository;
import com.accounting.service.SchedularService;
import com.accounting.util.ChartHelper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.data.Invoice;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;

@Service

public class SchedularServiceImpl implements SchedularService {

	private InvoiceRepository invoiceRepository;

	private ObjectMapper mapper;

	private Connections connections;

	@Autowired
	private MongoTemplate mongoTemplate;

	private ModelMapper modelMapper;

	private ChartHelper chartHelper;

	@Autowired
	public SchedularServiceImpl(Connections connections, CustomerRepo customerRepo,
			InvoiceRepository invoiceRepository) {
		this.modelMapper = new ModelMapper();
		this.mapper = new ObjectMapper();
		this.connections = connections;
		this.invoiceRepository = invoiceRepository;
		this.chartHelper = new ChartHelper();

	}

	@Override
	public List<LocalInvoice> getInvoicesWithCreatedStatus() {
		List<LocalInvoice> localInvoices = new ArrayList<>();
		Query query = new Query();
		query.addCriteria(Criteria.where("status").is("created"));
		localInvoices = mongoTemplate.find(query, LocalInvoice.class);
		return localInvoices;
	}

	@Override
	public Invoice saveInvoiceToQuickBook(InvoiceModal invoiceModal) throws FMSException {
		
			DataService dataService = connections.createConnection();
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			Invoice invoice = mapper.convertValue(invoiceModal, Invoice.class);

			Invoice resultInvoice = dataService.add(invoice);
			return resultInvoice;
			}

	@Override
	public InvoiceModal findInvoiceById(String id) {

		if (!invoiceRepository.existsById(id)) {
			return null;
		}
		LocalInvoice invoice = invoiceRepository.findById(id).get();
		InvoiceModal invoiceModal = modelMapper.map(invoice, InvoiceModal.class);
		return invoiceModal;
	}

	@Override
	public void saveCustomerId(String id, String localInvoiceId) {
		LocalInvoice result = invoiceRepository.findById(localInvoiceId).get();
		if (result != null) {
			result.setStatus("uploaded");
			result.setInvoiceId(id);
			invoiceRepository.save(result);
		}
	}

	@Override
	public Customer saveCustomerToQuickBook(LocalCustomerModal customerModal) throws FMSException {
		
			DataService dataService = connections.createConnection();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			Customer customer = mapper.convertValue(customerModal, Customer.class);
			return dataService.add(customer);
		

	}

	@Override
	public Customer updateCustomerToQuickBook(Customer customer) throws FMSException {
        
		DataService dataService = connections.createConnection();
		Customer resultCustomer = dataService.add(customer);
		return resultCustomer;
  
	}

	@Override
	public boolean generateCharts() {
		List<Data> list = customerCount();
		if (!list.isEmpty()) {
			chartHelper.generatePieChart(list, "D:\\charts");
			return true;
		}

		return false;
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

}
