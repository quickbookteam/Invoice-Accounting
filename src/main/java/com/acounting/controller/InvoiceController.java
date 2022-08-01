package com.acounting.controller;

import com.acounting.modal.CommonResponse;
import com.acounting.modal.invoice.InvoiceModal;
import com.acounting.service.InvoiceService;
import com.acounting.util.Helper;
import com.intuit.ipp.data.Invoice;
import com.intuit.ipp.services.DataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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