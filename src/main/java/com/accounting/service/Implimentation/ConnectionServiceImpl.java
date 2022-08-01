package com.accounting.service.Implimentation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accounting.entity.Connection;
import com.accounting.modal.ConnectionModal;
import com.accounting.repositery.ConnectionRepositery;
import com.accounting.repositery.CustomerRepo;
import com.accounting.service.ConnectionService;
import com.accounting.util.ChartHelper;
import com.accounting.util.Helper;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Qualifier("connectionImplementation")
public class ConnectionServiceImpl implements ConnectionService {

	
	ConnectionRepositery connectionRepositery;

	ModelMapper modelMapper;

	ObjectMapper mapper;

	@Autowired
	public ConnectionServiceImpl(@Qualifier("connectionRepository")ConnectionRepositery connectionRepositery) {
		this.connectionRepositery = connectionRepositery;
		this.modelMapper = new ModelMapper();
		this.mapper = new ObjectMapper();
		

	}

	@Override
	public ResponseEntity<?> save(ConnectionModal connection) {
		Connection con = modelMapper.map(connection, Connection.class);
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
		return connectionRepositery.findById(1L).get();
		}
	
	@Override
	public ConnectionModal get(Long id) {
		if(!connectionRepositery.existsById(id)) {
			return null;
		}
	   return new ModelMapper().map(connectionRepositery.findById(id).get(), ConnectionModal.class);
	}

	@Override
	public ConnectionModal updateConnectionInfo(ConnectionModal connectionModel) {
		connectionRepositery.save(new ModelMapper().map(connectionModel, Connection.class));		
	   return connectionModel;
	}

}
