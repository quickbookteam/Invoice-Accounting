package com.accounting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accounting.entity.invoice.LocalInvoice;
import com.accounting.modal.Data;
import com.accounting.modal.customer.LocalCustomerModal;
import com.accounting.modal.invoice.InvoiceModal;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.data.Invoice;
import com.intuit.ipp.exception.FMSException;

public interface SchedularService {
	List<LocalInvoice> getInvoicesWithCreatedStatus();

	InvoiceModal findInvoiceById(String id);

	Invoice saveInvoiceToQuickBook(InvoiceModal invoiceModal) throws FMSException;

	Customer saveCustomerToQuickBook(LocalCustomerModal customerModal) throws FMSException;

	void saveCustomerId(String id, String localInvoiceId);
	
	Customer updateCustomerToQuickBook(Customer customer) throws FMSException;
	
	public boolean generateCharts();
	
	public List<Data> customerCount();

}
