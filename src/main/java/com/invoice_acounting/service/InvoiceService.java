package com.invoice_acounting.service;

import com.intuit.ipp.data.Invoice;
import com.intuit.ipp.exception.FMSException;
import com.invoice_acounting.entity.invoice.LocalInvoice;
import com.invoice_acounting.modal.invoice.InvoiceModal;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InvoiceService {
	
    ResponseEntity<?> save(InvoiceModal invoice);
    
    ResponseEntity<?> findById(String id);


    List<LocalInvoice> findAll();

    Invoice saveInvoiceToQuickBook(InvoiceModal invoiceModal) throws FMSException;
    public void saveId(String id,String localInvoiceId);
}
