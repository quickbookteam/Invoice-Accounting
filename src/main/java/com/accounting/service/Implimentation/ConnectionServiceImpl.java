package com.accounting.service.Implimentation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accounting.entity.Connection;
import com.accounting.exception.CustomException;
import com.accounting.modal.ConnectionModal;
import com.accounting.repositery.ConnectionRepositery;
import com.accounting.service.ConnectionService;
import com.accounting.util.UtilConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConnectionServiceImpl implements ConnectionService {

	private ConnectionRepositery connectionRepositery;

	private ModelMapper modelMapper;

	@Autowired
	public ConnectionServiceImpl( ConnectionRepositery connectionRepositery) {
		this.connectionRepositery = connectionRepositery;
		this.modelMapper = new ModelMapper();

	}

	@Override
	public ResponseEntity<?> save(ConnectionModal connection) {
		Connection con = modelMapper.map(connection, Connection.class);
		connectionRepositery.save(con);

		return new ResponseEntity<>("Added", HttpStatus.OK);
	}

	@Override
	public Connection getDetails() {
         log.info("Inside connection get details");
		return connectionRepositery.findById(1L).get();
	}

	@Override
	public ConnectionModal get(Long id) {
		log.info("Insidse connection model get method");
		if (!connectionRepositery.existsById(id)) {
			return null;
		}
		return new ModelMapper().map(connectionRepositery.findById(id).get(), ConnectionModal.class);
	}

	@Override
	public ConnectionModal updateConnectionInfo(ConnectionModal connectionModel) {
		log.info("inside connection update ");
		connectionRepositery.save(new ModelMapper().map(connectionModel, Connection.class));
		return connectionModel;
	}

}
