package com.accounting.service.Implimentation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accounting.connection.IConnections;
import com.accounting.entity.transaction.LocalTransaction;
import com.accounting.modal.CommonResponse;
import com.accounting.modal.transaction.LocalTransactionModel;
import com.accounting.repositery.TransactionRepository;
import com.accounting.service.TransactionServices;
import com.accounting.util.UtilConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.intuit.ipp.data.Payment;
import com.intuit.ipp.exception.FMSException;

@Service
public class TransactionServiceImpl implements TransactionServices {

	private TransactionRepository transactionRepository;

	private IConnections iConnections;

	private ModelMapper modelMapper;

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	public TransactionServiceImpl(TransactionRepository transactionRepository, IConnections iConnections) {
		this.transactionRepository = transactionRepository;
		this.iConnections = iConnections;
		this.modelMapper = new ModelMapper();
	}

	@Override
	public ResponseEntity<CommonResponse> saveTransaction(LocalTransactionModel localTransactionModel)
			throws FMSException {
		
		Payment payment = modelMapper.map(localTransactionModel, Payment.class);
		LocalTransaction localTransaction = modelMapper.map( iConnections.createConnection().add(payment),
			LocalTransaction.class);
		transactionRepository.save(localTransaction);
		CommonResponse response = new CommonResponse(localTransaction, UtilConstants.TRANSECTION_SAVED);
		return new ResponseEntity<CommonResponse>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CommonResponse> getTransaction(String id) {
		LocalTransaction localTransaction = transactionRepository.findByTransactionId(id);
		CommonResponse response = new CommonResponse(modelMapper.map(localTransaction, LocalTransactionModel.class),
				UtilConstants.TRANSECTION_FOUND);
		return new ResponseEntity<CommonResponse>(response, HttpStatus.OK);
	}
}
