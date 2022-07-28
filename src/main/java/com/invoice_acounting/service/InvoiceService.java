package com.invoice_acounting.service;

import com.intuit.ipp.data.Invoice;
import com.intuit.ipp.exception.FMSException;
import com.invoice_acounting.entity.invoice.LocalInvoice;
import com.invoice_acounting.exception.InvoiceNotFound;
import com.invoice_acounting.modal.CommonResponse;
import com.invoice_acounting.modal.invoice.InvoiceModal;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface InvoiceService {
	
	ResponseEntity<CommonResponse> save(InvoiceModal invoice);
    
	ResponseEntity<CommonResponse> findById(String id);


    List<LocalInvoice> findAll();

   
    public void saveId(String id,String localInvoiceId);


}
