package com.invoice_acounting.service.Implimentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

import com.invoice_acounting.dao.ConnectionDao;
import com.invoice_acounting.modal.Connection;
import com.invoice_acounting.service.ConnectionService;

@Service
public class ConnectionServiceImpl implements ConnectionService{
	
	@Autowired
	ConnectionDao connectionDao;

	@Override
	public ResponseEntity<?> save(Connection connection) {
		return connectionDao.save(connection);
	}

	@Override
	public Connection get(Long id) {
		return connectionDao.get(id);
	}

}
