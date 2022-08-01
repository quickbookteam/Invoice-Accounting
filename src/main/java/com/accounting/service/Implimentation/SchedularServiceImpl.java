package com.accounting.service.Implimentation;

import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.impl.jam.mutable.MPackage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.accounting.entity.invoice.LocalInvoice;
import com.accounting.modal.customer.LocalCustomerModal;
import com.accounting.modal.invoice.InvoiceModal;
import com.accounting.repositery.CustomerRepo;
import com.accounting.repositery.InvoiceRepository;
import com.accounting.service.SchedularService;
import com.accounting.util.Helper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.data.Invoice;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;

@Service
@Qualifier("schedularServiceImpl")
public class SchedularServiceImpl implements SchedularService {
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private  CustomerRepo customerRepo;
	
	private ObjectMapper mapper;
	private Helper helper;
	@Autowired
	private MongoTemplate mongoTemplate;
	private ModelMapper modelMapper;

	SchedularServiceImpl(@Qualifier("helper") Helper helper) {
		this.helper = helper;
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
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		Invoice invoice = mapper.convertValue(invoiceModal, Invoice.class);
		System.out.println(" rusia === "+invoice.getCustomerRef().getName());

		Invoice invoice1 = dataService.add(invoice);
		System.out.println(" Invoice shivani-====== "+ invoice1.getCustomerRef().getName());
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

		System.out.println("????????????"+localInvoiceId);
		LocalInvoice result = invoiceRepository.findById(localInvoiceId).get();
//		System.out.println("#######"+result.get_id());
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