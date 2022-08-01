package com.accounting.controller;

import com.accounting.modal.CommonResponse;
import com.accounting.modal.invoice.InvoiceModal;
import com.accounting.service.InvoiceService;
import com.accounting.util.Helper;
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
//	@PostMapping ("/invoice2")
//	public Invoice addInvoice(@RequestBody Invoice invoice) throws Exception {
//		DataService dataService = helper.getConnection();
//		dataService.add(invoice);
//		return invoice;
//	}
//
//	@GetMapping ("/invoice/{id}")
//	public Invoice getInvoice(@PathVariable("id") String id) throws Exception {
//		DataService dataService = helper.getConnection();
//		Invoice invoice=new Invoice();
//		invoice.setId(id);
//		Invoice invoice1=dataService.findById(invoice);
//		return invoice1;
//	}
//	@GetMapping ("/invoice/db/{id}")
//	public com.invoice_acounting.entity.invoice.Invoice getInvoice1(@PathVariable("id") String id) throws Exception {
//		DataService dataService = helper.getConnection();
//		com.invoice_acounting.entity.invoice.Invoice invoice1=invoiceService.findById(id);
//		return invoice1;
//	}
//
	
	
//	@PostMapping("/invoice2")
//	public ResponseEntity<?> addInvoice2(@RequestBody com.invoice_acounting.entity.invoice.Invoice invoice) throws Exception
//	{
//		invoiceService.save(invoice);
//		return new ResponseEntity<>(invoice, HttpStatus.OK);
//	}
//
//	@DeleteMapping("/invoice")
//	public Invoice delete(@RequestBody Invoice invoice) throws Exception {
//		DataService dataService =helper.getConnection();
//		dataService.delete(invoice);
//		return invoice;
//	}
//	@PutMapping("/invoice")
//	public Invoice update(@RequestBody Invoice invoice) throws Exception {
//		DataService dataService = helper.getConnection();
//		dataService.update(invoice);
//		return invoice;
//	}
}