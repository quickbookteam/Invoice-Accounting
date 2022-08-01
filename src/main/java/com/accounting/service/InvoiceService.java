package com.accounting.service;

import com.accounting.entity.invoice.LocalInvoice;
import com.accounting.exception.InvoiceNotFound;
import com.accounting.modal.CommonResponse;
import com.accounting.modal.invoice.InvoiceModal;
import com.intuit.ipp.data.Invoice;
import com.intuit.ipp.exception.FMSException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface InvoiceService {
	
	ResponseEntity<CommonResponse> save(InvoiceModal invoice);
    
	ResponseEntity<CommonResponse> findById(String id);


    List<LocalInvoice> findAll();

   
    public void saveId(String id,String localInvoiceId);


}
