package com.invoice_acounting.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.ipp.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.intuit.ipp.services.DataService;
import com.invoice_acounting.config.QuickBookIntegration;

@RestController
public class InvoiceController {

	@Autowired
	QuickBookIntegration quickBookIntegration;

	DataService dataService;

	@RequestMapping("/add")
	public Invoice addInvoice(@RequestBody Invoice invoice) throws Exception {
		DataService dataService = quickBookIntegration.demo();
		dataService.add(invoice);
		return invoice;
	}

}