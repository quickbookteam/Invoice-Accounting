package com.accounting.scheduler;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.accounting.entity.invoice.LocalInvoice;
import com.accounting.modal.customer.LocalCustomerModal;
import com.accounting.modal.invoice.InvoiceModal;
import com.accounting.service.SchedularService;
import com.accounting.service.Implimentation.SchedularServiceImpl;
import com.accounting.util.Helper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.ipp.data.Invoice;
import com.intuit.ipp.exception.FMSException;

@Configuration
public class InvoiceScheduler {

	SchedularService schedularService;
	ModelMapper modelMapper;

	@Autowired
	InvoiceScheduler(SchedularService schedularService) {
		this.schedularService = schedularService;
		this.modelMapper = new ModelMapper();

	}

	@Scheduled(cron = "* * * ? * *") // after every second

	public Invoice saveInvoiceToQuickBookServer() throws FMSException {

		List<LocalInvoice> localInvoices = schedularService.getInvoices_With_Created_Status();
		for (LocalInvoice localInvoice : localInvoices) {
			InvoiceModal invoiceModal = modelMapper.map(localInvoice, InvoiceModal.class);

			Invoice invoice = schedularService.saveInvoiceToQuickBook(invoiceModal);
			schedularService.saveId(invoice.getId(), localInvoice.getId());
		}

		return null;
	}
}