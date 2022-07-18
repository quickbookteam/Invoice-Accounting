package com.invoice_acounting.controller;

import com.intuit.ipp.data.*;
import com.invoice_acounting.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.intuit.ipp.services.DataService;
import com.invoice_acounting.util.Helper;

@RestController
public class InvoiceController {

	@Autowired
    Helper helper;

	@Autowired
	InvoiceService invoiceService;


	@GetMapping ("/invoice/{id}")
	public Invoice getInvoice(@PathVariable("id") String id) throws Exception {
		DataService dataService = helper.getConnection();
		Invoice invoice=new Invoice();
		invoice.setId(id);
		Invoice invoice1=dataService.findById(invoice);
		return invoice1;
	}
	@GetMapping ("/invoice/db/{id}")
	public com.invoice_acounting.entity.invoice.Invoice getInvoice1(@PathVariable("id") String id) throws Exception {
		DataService dataService = helper.getConnection();
		com.invoice_acounting.entity.invoice.Invoice invoice1=invoiceService.findById(id);
		return invoice1;
	}

	@PostMapping ("/invoice")
	public Invoice addInvoice(@RequestBody Invoice invoice) throws Exception {
		DataService dataService = helper.getConnection();
		dataService.add(invoice);
		return invoice;
	}
	@PostMapping("/invoice2")
	public ResponseEntity<?> addInvoice2(@RequestBody com.invoice_acounting.entity.invoice.Invoice invoice) throws Exception
	{
		invoiceService.save(invoice);
		return new ResponseEntity<>(invoice, HttpStatus.OK);
	}

	@DeleteMapping("/invoice")
	public Invoice delete(@RequestBody Invoice invoice) throws Exception {
		DataService dataService =helper.getConnection();
		dataService.delete(invoice);
		return invoice;
	}
	@PutMapping("/invoice")
	public Invoice update(@RequestBody Invoice invoice) throws Exception {
		DataService dataService = helper.getConnection();
		dataService.update(invoice);
		return invoice;
	}
}