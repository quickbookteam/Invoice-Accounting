package com.invoice_acounting.service.Implimentation;

import java.util.ArrayList;
import java.util.List;

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
import com.intuit.ipp.data.Invoice;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.invoice_acounting.entity.invoice.LocalInvoice;
import com.invoice_acounting.exception.CustomerNotFoundException;
import com.invoice_acounting.exception.InvoiceNotFoundException;
import com.invoice_acounting.modal.invoice.InvoiceModal;
import com.invoice_acounting.repositery.CustomerRepo;
import com.invoice_acounting.repositery.InvoiceRepository;
import com.invoice_acounting.service.InvoiceService;
import com.invoice_acounting.util.Helper;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	Helper helper;

	ModelMapper modelMapper;

	@Autowired
	InvoiceRepository invoiceRepository;

	@Autowired
	CustomerRepo customerRepositery;

	@Autowired
	MongoTemplate mongoTemplate;

	InvoiceServiceImpl() {
		this.helper = new Helper();
		this.modelMapper = new ModelMapper();
	}

	@Override
	public ResponseEntity<?> save(InvoiceModal invoiceModal) {
//		if (customerRepositery.findByCustomerId(invoiceModal.getCustomerRef().getValue()) == null) {
//			return new ResponseEntity<>("Customer not valid", HttpStatus.BAD_REQUEST);
//		}
		LocalInvoice invoice = modelMapper.map(invoiceModal, LocalInvoice.class);
		invoice.setInvoiceId("0");
		invoice.setStatus("created");
		LocalInvoice result = invoiceRepository.save(invoice);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<InvoiceModal> findById(String id) {

		if (!invoiceRepository.existsById(id)) {
			throw new InvoiceNotFoundException();
		}
		LocalInvoice invoice = invoiceRepository.findById(id).get();
		InvoiceModal invoiceModal = modelMapper.map(invoice, InvoiceModal.class);
		return new ResponseEntity<InvoiceModal>(invoiceModal, HttpStatus.OK);
	}

	@Override
	public List<LocalInvoice> findAll() {
		List<LocalInvoice> localinvoice=invoiceRepository.findAll();
		boolean check = localinvoice.isEmpty();
		if(check==false) {
		return localinvoice;
		}
		else
		{
			throw new InvoiceNotFoundException();
		}
	}



	@Override
	public void saveId(String id, String localInvoiceId) {
		
		LocalInvoice result = invoiceRepository.findById(localInvoiceId).get();
		if(result!=null) {
			result.setStatus("uploaded");
			result.setInvoiceId(id);
			invoiceRepository.save(result);
		}
		else {
			throw new InvoiceNotFoundException();
		}
	}

	
}
