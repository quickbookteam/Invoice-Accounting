package com.invoice_acounting.service;

import java.util.List;

import com.intuit.ipp.data.Invoice;
import com.intuit.ipp.exception.FMSException;
import com.invoice_acounting.entity.invoice.LocalInvoice;
import com.invoice_acounting.modal.invoice.InvoiceModal;

public interface SchedularService {
	List<LocalInvoice> getInvoices_With_Created_Status();
	
	InvoiceModal findInvoiceById(String id);
	
	  Invoice saveInvoiceToQuickBook(InvoiceModal invoiceModal) throws FMSException;

	void saveId(String id, String localInvoiceId);

}
