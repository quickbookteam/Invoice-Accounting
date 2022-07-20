package com.invoice_acounting.service;

import com.invoice_acounting.modal.invoice.InvoiceModal;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface InvoiceService {
	
    ResponseEntity<?> save(InvoiceModal invoice);
    
    ResponseEntity<?> findById(String id);
    
}
