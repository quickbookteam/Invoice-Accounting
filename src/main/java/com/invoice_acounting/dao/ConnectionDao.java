package com.invoice_acounting.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.invoice_acounting.modal.ConnectionModal;
import com.invoice_acounting.repositery.ConnectionRepositery;

@Service
public class ConnectionDao {
//	
//	@Autowired
//	ConnectionRepositery connectionRepo;
//	
//	public ResponseEntity<?> save(ConnectionModal connection) {
//		
//		com.invoice_acounting.entity.Connection con =new com.invoice_acounting.entity.Connection();
//		con.setId(1L);
//		con.setAccessToken(connection.getAccessToken());
//		//con.setRealmId(connection.getRealmeId());
//		con.setRefershToken(connection.getRefreshToken());
//		connectionRepo.save(con);
//				
//		return new ResponseEntity<>("Added",HttpStatus.OK);
//		
//	}
//	
//	public ConnectionModal get(Long id) {
//		Optional<com.invoice_acounting.entity.Connection> optional = connectionRepo.findById(id);
//		if(!optional.isEmpty()) {
//			com.invoice_acounting.entity.Connection connection =optional.get();
//			ConnectionModal con =new ConnectionModal();
//			con.setId(connection.getId());
//			con.setAccessToken(connection.getAccessToken());
//			//con.setRealmeId(connection.getRealmId());
//			con.setRefreshToken(connection.getRefershToken());
//			return con;
//		}
//		return null;
//	}

}
