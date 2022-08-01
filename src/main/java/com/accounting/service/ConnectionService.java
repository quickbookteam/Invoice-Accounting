package com.accounting.service;

import org.springframework.http.ResponseEntity;

import com.accounting.entity.Connection;
import com.accounting.modal.ConnectionModal;



public interface ConnectionService {
	
	public ResponseEntity<?> save(ConnectionModal connection);

	public Connection getDetails();
	
	public ConnectionModal get(Long id);

	public ConnectionModal updateConnectionInfo(ConnectionModal connectionModal);


}
