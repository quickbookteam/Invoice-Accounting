package com.acounting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acounting.entity.invoice.LocalInvoice;
import com.acounting.modal.customer.LocalCustomerModal;
import com.acounting.modal.invoice.InvoiceModal;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.data.Invoice;
import com.intuit.ipp.exception.FMSException;

public interface SchedularService {
	List<LocalInvoice> getInvoices_With_Created_Status();

	InvoiceModal findInvoiceById(String id);

	Invoice saveInvoiceToQuickBook(InvoiceModal invoiceModal) throws FMSException;

	Customer saveCustomerToQuickBook(LocalCustomerModal customerModal) throws FMSException;

	void saveId(String id, String localInvoiceId);

}
