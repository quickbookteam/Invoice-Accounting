package com.invoice_acounting.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.invoice_acounting.modal.Connection;

@Service
public interface ConnectionService {
	
	ResponseEntity<?> save(Connection connection);
	
	Connection get(Long id);
	
}
