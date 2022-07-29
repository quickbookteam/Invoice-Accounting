package com.invoice_acounting.controller;

import com.intuit.ipp.data.Invoice;
import com.intuit.ipp.services.DataService;
import com.invoice_acounting.modal.CommonResponse;
import com.invoice_acounting.modal.invoice.InvoiceModal;
import com.invoice_acounting.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.invoice_acounting.util.Helper;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class InvoiceController {

	

	@Autowired
	InvoiceService invoiceService;

	@PostMapping("/invoice")
	public ResponseEntity<CommonResponse> create(@RequestBody InvoiceModal invoiceModal) {
		log.info("inside add invoice", invoiceModal);
		return invoiceService.save(invoiceModal);
	}
	
	@GetMapping ("/invoice/{id}")
	public ResponseEntity<CommonResponse> getInvoice(@PathVariable("id") String id) throws Exception {
		log.info("inside get invoice by id");
		return invoiceService.findById(id);
	}

}