package com.accounting.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.accounting.entity.invoice.LocalInvoice;
import com.accounting.modal.CommonResponse;
import com.accounting.modal.invoice.InvoiceModal;

public interface InvoiceService {

	ResponseEntity<CommonResponse> save(InvoiceModal invoice);

	ResponseEntity<CommonResponse> findById(String id);

	List<LocalInvoice> findAll();

	void saveId(String id, String localInvoiceId);

}