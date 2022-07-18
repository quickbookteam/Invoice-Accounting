package com.invoice_acounting.dao;

import com.invoice_acounting.entity.invoice.Invoice;
import com.invoice_acounting.repositery.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InvoiceDao {

    @Autowired
    InvoiceRepository invoiceRepository;

    public ResponseEntity<Invoice> save(Invoice invoice){
       Invoice result= invoiceRepository.save(invoice);
        return new ResponseEntity<>(result,HttpStatus.OK);

    }
    public Invoice findById(String id){
        Invoice invoice=invoiceRepository.findById(id).get();
        return invoice;

    }

}
