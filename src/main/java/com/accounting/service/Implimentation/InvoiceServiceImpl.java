package com.accounting.service.Implimentation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accounting.entity.invoice.LocalInvoice;
import com.accounting.exception.CustomException;
import com.accounting.exception.CustomerNotFound;
import com.accounting.exception.InvoiceNotFound;
import com.accounting.modal.CommonResponse;
import com.accounting.modal.invoice.InvoiceModal;
import com.accounting.repositery.CustomerRepo;
import com.accounting.repositery.InvoiceRepository;
import com.accounting.service.InvoiceService;
import com.accounting.util.Helper;
import com.accounting.util.UtilConstants;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	private Helper helper;

	private ModelMapper modelMapper;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private CustomerRepo customerRepositery;

	@Autowired
	private MongoTemplate mongoTemplate;

	InvoiceServiceImpl() {
		this.helper = new Helper();
		this.modelMapper = new ModelMapper();
	}

	@Override
	public ResponseEntity<CommonResponse> save(InvoiceModal invoiceModal) {
		if (customerRepositery.findByCustomerId(invoiceModal.getCustomerRef().getValue()) == null) {
			throw new CustomerNotFound(UtilConstants.CUSTOMER_NOT_FOUND);
		}
		try {
			LocalInvoice invoice = modelMapper.map(invoiceModal, LocalInvoice.class);
			invoice.setInvoiceId("0");
			invoice.setStatus("created");
			LocalInvoice result = invoiceRepository.save(invoice);
			CommonResponse response = new CommonResponse(result,UtilConstants.INVOICE_SAVED);
			return new ResponseEntity<CommonResponse>(response, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
		
	}

	@Override
	public ResponseEntity<CommonResponse> findById(String id)   {

		if (!invoiceRepository.existsById(id)) {
			throw new InvoiceNotFound(UtilConstants.INVOICE_NOT_FOUND);
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
			return null;
//			throw new InvoiceException();
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
//			throw new InvoiceException();
		}
	}

	
}