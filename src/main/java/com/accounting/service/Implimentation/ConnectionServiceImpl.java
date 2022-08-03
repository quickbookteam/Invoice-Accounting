package com.accounting.service.Implimentation;

import com.accounting.exception.CustomException;
import com.accounting.modal.CommonResponse;
import com.accounting.util.UtilConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accounting.entity.Connection;
import com.accounting.modal.ConnectionModal;
import com.accounting.repositery.ConnectionRepositery;
import com.accounting.service.ConnectionService;

@Service
@Qualifier("connectionImplementation")
public class ConnectionServiceImpl implements ConnectionService {

    private ConnectionRepositery connectionRepositery;

    private ModelMapper modelMapper;

    @Autowired
    public ConnectionServiceImpl(@Qualifier("connectionRepository") ConnectionRepositery connectionRepositery) {
        this.connectionRepositery = connectionRepositery;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public ResponseEntity<?> save(ConnectionModal connection) {
        try {
            Connection con = modelMapper.map(connection, Connection.class);
            connectionRepositery.save(con);
            CommonResponse response = new CommonResponse(con, UtilConstants.CONNECTION_STABLISHED);
            return new ResponseEntity<CommonResponse>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public Connection getDetails() {
        Connection connection = connectionRepositery.findById(1L).get();
        if (!connection.equals(null)) {
            return connection;
        } else {
            throw new CustomException("Connection Not Found");
        }
    }

    @Override
    public ConnectionModal get(Long id) {
        if (!connectionRepositery.existsById(id)) {
            throw new CustomException(UtilConstants.CONNECTION_NOT_FOUND);
        }
        return new ModelMapper().map(connectionRepositery.findById(id).get(), ConnectionModal.class);
    }

    @Override
    public ConnectionModal updateConnectionInfo(ConnectionModal connectionModel) {
        try {
            connectionRepositery.save(new ModelMapper().map(connectionModel, Connection.class));
            return connectionModel;
        }catch (Exception e){
            throw  new CustomException(e.getMessage());
        }
    }
}
