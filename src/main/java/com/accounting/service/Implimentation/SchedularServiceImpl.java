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

import com.accounting.connection.IConnections;
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
import com.accounting.util.UtilConstants;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.data.Invoice;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SchedularServiceImpl implements SchedularService {

	private InvoiceRepository invoiceRepository;

	private ObjectMapper mapper;

	private IConnections iConnections;

	@Autowired
	private MongoTemplate mongoTemplate;

	private ModelMapper modelMapper;

	private ChartHelper chartHelper;

	@Autowired
	public SchedularServiceImpl(IConnections iConnections, CustomerRepo customerRepo,
			InvoiceRepository invoiceRepository) {
		this.modelMapper = new ModelMapper();
		this.mapper = new ObjectMapper();
		this.iConnections = iConnections;
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
		try {
			log.info("Save invoice to quickbook server");
			DataService dataService = iConnections.createConnection();
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			Invoice invoice = mapper.convertValue(invoiceModal, Invoice.class);

			Invoice resultInvoice = dataService.add(invoice);
			log.info("Invoice saved successfully to quickbook server");
			return resultInvoice;
			}
		    catch(Exception e) {
		    	throw new CustomException(UtilConstants.CUSTOMER_ADD_FAILED);
		    }
		}

	@Override
	public InvoiceModal findInvoiceById(String id) {

		if (!invoiceRepository.existsById(id)) {
			throw new CustomException(UtilConstants.INVOICE_NOT_FOUND);
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
		else {
			throw new CustomException(UtilConstants.INVOICE_NOT_FOUND);
		}
	}

	@Override
	public Customer saveCustomerToQuickBook(LocalCustomerModal customerModal) throws FMSException {
		try {
			log.info("Inside save customer save to Quickbook server");
		DataService dataService = iConnections.createConnection();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			Customer customer = mapper.convertValue(customerModal, Customer.class);
			log.info("customer saved to Quickbook server");
			return dataService.add(customer);
		}
		catch(Exception e) {
			throw new CustomException(e.getMessage());
		}
	}

	@Override
	public Customer updateCustomerToQuickBook(Customer customer) throws FMSException {
		try {
			log.info("Update Customer to Quckbook server");
		DataService dataService = iConnections.createConnection();
		Customer resultCustomer = dataService.add(customer);
		log.info("Upadted successfully");
		return resultCustomer;
		}
		catch(Exception e) {
			throw new CustomException(e.getMessage());
		}
	}

	@Override
	public boolean generateCharts() {
		log.info("Inside chart genration");
		List<Data> list = customerCount();
		if (!list.isEmpty()) {
			chartHelper.generatePieChart(list, "D:\\charts");
			return true;
		}
        log.info("Chart genartion failed");
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
