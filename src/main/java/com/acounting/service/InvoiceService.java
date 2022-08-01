package com.acounting.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.acounting.entity.invoice.LocalInvoice;
import com.acounting.modal.CommonResponse;
import com.acounting.modal.invoice.InvoiceModal;

public interface InvoiceService {

	ResponseEntity<CommonResponse> save(InvoiceModal invoice);

	ResponseEntity<CommonResponse> findById(String id);

	List<LocalInvoice> findAll();

	void saveId(String id, String localInvoiceId);

}