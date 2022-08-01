package com.acounting.service;

import com.acounting.entity.invoice.LocalInvoice;
import com.acounting.exception.InvoiceNotFound;
import com.acounting.modal.CommonResponse;
import com.acounting.modal.invoice.InvoiceModal;
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
