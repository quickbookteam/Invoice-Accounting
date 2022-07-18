package com.invoice_acounting.service;

import com.intuit.ipp.exception.FMSException;
import com.invoice_acounting.entity.customer.Customer;
import com.invoice_acounting.entity.invoice.Invoice;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface InvoiceService {
    ResponseEntity<?> save(Invoice invoice);
    Invoice findById(String id);
}
