package com.invoice_acounting.dao;

import com.invoice_acounting.entity.invoice.LocalInvoice;
import com.invoice_acounting.modal.invoice.InvoiceModal;
import com.invoice_acounting.repositery.InvoiceRepository;
import com.invoice_acounting.util.Helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class InvoiceDao {

    @Autowired
    InvoiceRepository invoiceRepository;
    
    @Autowired
    Helper helper;

    public ResponseEntity<LocalInvoice> save(InvoiceModal invoiceModal){
       LocalInvoice invoice = helper.getMapper().map(invoiceModal, LocalInvoice.class);
       invoice.setInvoice_id("0");
       invoice.setStatus("created");
       LocalInvoice result= invoiceRepository.save(invoice);
       return new ResponseEntity<>(result,HttpStatus.OK);
    }
    
    public ResponseEntity<?> findById(String id){
    	if(!invoiceRepository.existsById(id)) {
    		return new ResponseEntity<>("No Invoice found",HttpStatus.BAD_REQUEST);
    	}
        LocalInvoice invoice=invoiceRepository.findById(id).get();
        return  new ResponseEntity<>(helper.getMapper().map(invoice, InvoiceModal.class),HttpStatus.OK);
    }

}
