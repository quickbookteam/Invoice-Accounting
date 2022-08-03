package com.accounting.service.Implimentation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accounting.entity.invoice.LocalInvoice;
import com.accounting.exception.CustomException;
import com.accounting.exception.CustomerNotFoundException;
import com.accounting.exception.InvoiceNotFoundException;
import com.accounting.modal.CommonResponse;
import com.accounting.modal.invoice.InvoiceModal;
import com.accounting.repositery.CustomerRepo;
import com.accounting.repositery.InvoiceRepository;
import com.accounting.service.InvoiceService;
import com.accounting.util.UtilConstants;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	private ModelMapper modelMapper;

	private InvoiceRepository invoiceRepository;

	private CustomerRepo customerRepositery;

	@Autowired
	public InvoiceServiceImpl(InvoiceRepository invoiceRepository, CustomerRepo customerRepositery) {

		this.modelMapper = new ModelMapper();
		this.invoiceRepository = invoiceRepository;
		this.customerRepositery = customerRepositery;

	}

	@Override
	public ResponseEntity<CommonResponse> save(InvoiceModal invoiceModal) {
		if (customerRepositery.findByCustomerId(invoiceModal.getCustomerRef().getValue()) == null) {
			throw new CustomerNotFoundException(UtilConstants.CUSTOMER_NOT_FOUND);
		}
		try {
			LocalInvoice invoice = modelMapper.map(invoiceModal, LocalInvoice.class);
			invoice.setInvoiceId("0");
			invoice.setStatus("created");
			LocalInvoice result = invoiceRepository.save(invoice);
			CommonResponse response = new CommonResponse(result, UtilConstants.INVOICE_SAVED);
			return new ResponseEntity<CommonResponse>(response, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}

	}

	@Override
	public ResponseEntity<CommonResponse> findById(String id) {

		try {
			LocalInvoice invoice = invoiceRepository.findById(id).get();
			InvoiceModal invoiceModal = modelMapper.map(invoice, InvoiceModal.class);
			CommonResponse response = new CommonResponse(invoiceModal, UtilConstants.INVOICE_FOUND);
			return new ResponseEntity<CommonResponse>(response, HttpStatus.FOUND);
		} catch (Exception e) {
			throw new InvoiceNotFoundException(UtilConstants.INVOICE_NOT_FOUND);
		}

	}

}