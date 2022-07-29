package com.invoice_acounting.service;

import org.springframework.http.ResponseEntity;

import com.invoice_acounting.entity.Connection;
import com.invoice_acounting.modal.ConnectionModal;


public interface ConnectionService {
	
	 ResponseEntity<?> save(ConnectionModal connection);

	 Connection getDetails();
	
	 ConnectionModal get(Long id);

	 ConnectionModal updateConnectionInfo(ConnectionModal connectionModal);


}
