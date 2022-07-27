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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.data.Invoice;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.invoice_acounting.entity.customer.LocalCustomer;
import com.invoice_acounting.entity.invoice.LocalInvoice;
import com.invoice_acounting.exception.CustomerNotFoundException;
import com.invoice_acounting.modal.customer.LocalCustomerModal;
import com.invoice_acounting.modal.invoice.InvoiceModal;
import com.invoice_acounting.repositery.CustomerRepo;
import com.invoice_acounting.repositery.InvoiceRepository;
import com.invoice_acounting.service.SchedularService;
import com.invoice_acounting.util.Helper;

@Service("SchedularServiceImpl")
@Qualifier("schedularServiceImplementation")
public class SchedularServiceImpl implements SchedularService {
	@Autowired
	InvoiceRepository invoiceRepository;
	
	@Autowired
	public  CustomerRepo customerRepo;
	
	ObjectMapper mapper;
	Helper helper;
	@Autowired
	MongoTemplate mongoTemplate;
	ModelMapper modelMapper;

	SchedularServiceImpl() {
		this.helper = new Helper();
		this.modelMapper = new ModelMapper();
		this.mapper = new ObjectMapper();
	}

	@Override
	public List<LocalInvoice> getInvoices_With_Created_Status() {
		List<LocalInvoice> localInvoices = new ArrayList<>();
		Query query = new Query();
		query.addCriteria(Criteria.where("status").is("created"));
		localInvoices = mongoTemplate.find(query, LocalInvoice.class);
		System.out.println(localInvoices);
		return localInvoices;
	}

	@Override
	public Invoice saveInvoiceToQuickBook(InvoiceModal invoiceModal) throws FMSException {
		DataService dataService = helper.getConnection();
		ObjectMapper mapper = new ObjectMapper();

		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		Invoice invoice = mapper.convertValue(invoiceModal, Invoice.class);

		Invoice invoice1 = dataService.add(invoice);
		return invoice1;
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
	public void saveId(String id, String localInvoiceId) {
		LocalInvoice result = invoiceRepository.findById(localInvoiceId).get();
		result.setStatus("uploaded");
		result.setInvoiceId(id);
		invoiceRepository.save(result);
	}

	@Override
	public Customer saveCustomerToQuickBook(LocalCustomerModal customerModal) throws FMSException {
		DataService dataService = helper.getConnection();

		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		Customer customer = mapper.convertValue(customerModal, Customer.class);

		
		return dataService.add(customer);
		
	}
	
	

}
