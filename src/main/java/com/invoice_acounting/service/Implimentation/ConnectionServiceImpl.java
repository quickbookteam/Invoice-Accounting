package com.invoice_acounting.service.Implimentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.invoice_acounting.entity.Connection;
import com.invoice_acounting.modal.ConnectionModal;
import com.invoice_acounting.repositery.ConnectionRepositery;
import com.invoice_acounting.service.ConnectionService;

@Service("ConnectionServiceImpl")

public class ConnectionServiceImpl implements ConnectionService {

	@Autowired
	ConnectionRepositery connectionRepositery;

	@Override
	public ResponseEntity<?> save(ConnectionModal connection) {
		Connection con = new Connection();
		con.setId(1L);
		con.setAccessToken(connection.getAccessToken());
		con.setRealmId(connection.getRealmeId());
		con.setRefershToken(connection.getRefreshToken());
		connectionRepositery.save(con);

		return new ResponseEntity<>("Added", HttpStatus.OK);
	}

//	@Override
//	public Connection get(Long id) {
//		Optional<com.invoice_acounting.entity.Connection> optional = connectionRepo.findById(id);
//		if (!optional.isEmpty()) {
//			com.invoice_acounting.entity.Connection connection = optional.get();
//			Connection con = new Connection();
//			con.setId(connection.getId());
//			con.setAccessToken(connection.getAccessToken());
//			con.setRealmeId(connection.getRealmId());
//			con.setRefreshToken(connection.getRefershToken());
//			return con;
//		}
//		return null;
//	}

	@Override
	public Connection getDetails() {
		System.out.println(connectionRepositery);

		return connectionRepositery.findById(1L).get();
		}


}
