package com.invoice_acounting.service.Implimentation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.invoice_acounting.entity.invoice.LocalInvoice;
import com.invoice_acounting.exception.InvoiceException;
import com.invoice_acounting.modal.CommonResponse;
import com.invoice_acounting.modal.invoice.InvoiceModal;
import com.invoice_acounting.repositery.CustomerRepo;
import com.invoice_acounting.repositery.InvoiceRepository;
import com.invoice_acounting.service.InvoiceService;
import com.invoice_acounting.util.Helper;
import com.invoice_acounting.util.UtilConstants;

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
	public ResponseEntity<CommonResponse> save(InvoiceModal invoiceModal) {
		if (customerRepositery.findByCustomerId(invoiceModal.getCustomerRef().getValue()) == null) {
			throw new InvoiceException(UtilConstants.CUSTOMER_NOT_FOUND,HttpStatus.NOT_FOUND);
		}
		LocalInvoice invoice = modelMapper.map(invoiceModal, LocalInvoice.class);
		invoice.setInvoiceId("0");
		invoice.setStatus("created");
		LocalInvoice result = invoiceRepository.save(invoice);
		CommonResponse response = new CommonResponse(result,UtilConstants.INVOICE_SAVED);
		return new ResponseEntity<CommonResponse>(response, HttpStatus.ACCEPTED);
		
	}

	@Override
	public ResponseEntity<CommonResponse> findById(String id) {

		if (!invoiceRepository.existsById(id)) {
			throw new InvoiceException(UtilConstants.INVOICE_NOT_FOUND,HttpStatus.NOT_FOUND);
		}
		LocalInvoice invoice = invoiceRepository.findById(id).get();
		InvoiceModal invoiceModal = modelMapper.map(invoice, InvoiceModal.class);
		CommonResponse response = new CommonResponse(invoiceModal,UtilConstants.INVOICE_FOUND);
		return new ResponseEntity<CommonResponse>(response, HttpStatus.FOUND);
		
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
			throw new InvoiceException();
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
			throw new InvoiceException();
		}
	}

	
}
