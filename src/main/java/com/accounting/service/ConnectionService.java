package com.accounting.service;

import org.springframework.http.ResponseEntity;

import com.accounting.entity.Connection;
import com.accounting.modal.ConnectionModal;

public interface ConnectionService {

	ResponseEntity<?> save(ConnectionModal connection);

	Connection getDetails();

	ConnectionModal get(Long id);

	ConnectionModal updateConnectionInfo(ConnectionModal connectionModal);

}
