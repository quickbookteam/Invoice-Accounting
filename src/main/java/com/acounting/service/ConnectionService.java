package com.acounting.service;

import org.springframework.http.ResponseEntity;

import com.acounting.entity.Connection;
import com.acounting.modal.ConnectionModal;



public interface ConnectionService {
	
	public ResponseEntity<?> save(ConnectionModal connection);

	public Connection getDetails();
	
	public ConnectionModal get(Long id);

	public ConnectionModal updateConnectionInfo(ConnectionModal connectionModal);


}
