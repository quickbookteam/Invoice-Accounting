package com.accounting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accounting.modal.CommonResponse;
import com.accounting.modal.invoice.InvoiceModal;
import com.accounting.service.InvoiceService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class InvoiceController {

	InvoiceService invoiceService;

	@Autowired
	public InvoiceController(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	@PostMapping("/invoice")
	public ResponseEntity<CommonResponse> create(@RequestBody InvoiceModal invoiceModal) {
		log.info("inside add invoice", invoiceModal);
		return invoiceService.save(invoiceModal);
	}

	@GetMapping("/invoice/{id}")
	public ResponseEntity<CommonResponse> getInvoice(@PathVariable("id") String id) throws Exception {
		log.info("inside get invoice by id");
		return invoiceService.findById(id);
	}

}