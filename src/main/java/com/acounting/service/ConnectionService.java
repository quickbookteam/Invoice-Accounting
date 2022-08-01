package com.acounting.service;

import org.springframework.http.ResponseEntity;

import com.acounting.entity.Connection;
import com.acounting.modal.ConnectionModal;


public interface ConnectionService {
	
	 ResponseEntity<?> save(ConnectionModal connection);

	 Connection getDetails();
	
	 ConnectionModal get(Long id);

	 ConnectionModal updateConnectionInfo(ConnectionModal connectionModal);


}
