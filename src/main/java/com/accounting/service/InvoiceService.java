package com.accounting.service;

import org.springframework.http.ResponseEntity;

import com.accounting.modal.CommonResponse;
import com.accounting.modal.invoice.InvoiceModal;

public interface InvoiceService {

	ResponseEntity<CommonResponse> save(InvoiceModal invoice);

	ResponseEntity<CommonResponse> findById(String id);

	

	

}