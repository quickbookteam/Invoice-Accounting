package com.accounting.scheduler;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.accounting.entity.invoice.LocalInvoice;
import com.accounting.exception.CustomException;
import com.accounting.modal.invoice.InvoiceModal;
import com.accounting.service.SchedularService;
import com.intuit.ipp.data.Invoice;
import com.intuit.ipp.exception.FMSException;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class InvoiceScheduler implements Runnable{

	private SchedularService schedularService;
	private ModelMapper modelMapper;

	@Autowired
	InvoiceScheduler(SchedularService schedularService) {
		this.schedularService = schedularService;
		this.modelMapper = new ModelMapper();

	}

@Override
public void run() {
	List<LocalInvoice> localInvoices = schedularService.getInvoicesWithCreatedStatus();
	for (LocalInvoice localInvoice : localInvoices) {
		InvoiceModal invoiceModal = modelMapper.map(localInvoice, InvoiceModal.class);
		try {
			log.info("Invoice before save to quick book");
			Invoice invoice = schedularService.saveInvoiceToQuickBook(invoiceModal);
			log.info("Invoice after save to quickbook");
			schedularService.saveCustomerId(invoice.getId(), localInvoice.getId());
		} catch (Exception ex) {
			log.info(ex.getMessage());
			throw new CustomException(ex.getMessage());
		}
	}

}
}