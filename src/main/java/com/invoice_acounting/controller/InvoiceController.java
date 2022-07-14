package com.invoice_acounting.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.ipp.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.intuit.ipp.services.DataService;
import com.invoice_acounting.config.QuickBookIntegration;

@RestController
public class InvoiceController {

	@Autowired
	QuickBookIntegration quickBookIntegration;

	DataService dataService;

	@PostMapping ("/invoice")
	public Invoice addInvoice(@RequestBody Invoice invoice) throws Exception {
		DataService dataService = quickBookIntegration.demo();
		dataService.add(invoice);
		return invoice;
	}
	@DeleteMapping("/invoice")
	public Invoice delete(@RequestBody Invoice invoice) throws Exception {
		DataService dataService = quickBookIntegration.demo();
		dataService.delete(invoice);
		return invoice;
	}
	@PutMapping("/invoice")
	public Invoice update(@RequestBody Invoice invoice) throws Exception {
		DataService dataService = quickBookIntegration.demo();
		dataService.update(invoice);
		return invoice;
	}
}