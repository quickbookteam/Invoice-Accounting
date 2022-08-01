package com.accounting.service.Implimentation;

import com.accounting.entity.transaction.LocalTransaction;
import com.accounting.modal.transaction.LocalTransactionModel;
import com.accounting.repositery.TransactionRepository;
import com.accounting.service.TransactionServices;
import com.accounting.util.Helper;
import com.intuit.ipp.data.Payment;
import com.intuit.ipp.exception.FMSException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Qualifier("transactionServiceImplementation")
public class TransactionServiceImpl implements TransactionServices {

    private TransactionRepository transactionRepository;

    private Helper helper;

    private ModelMapper modelMapper;

    @Autowired
    MongoTemplate mongoTemplate;

    public TransactionServiceImpl(@Qualifier("transactionRepository") TransactionRepository transactionRepository,@Qualifier("helper")Helper helper) {
        this.transactionRepository = transactionRepository;
        this.helper=helper;
        this.modelMapper =new ModelMapper();
    }

    @Override
    public ResponseEntity<?> saveTransaction(LocalTransactionModel localTransactionModel) throws FMSException {
        Payment payment = modelMapper.map(localTransactionModel, Payment.class);
        LocalTransaction localTransaction = modelMapper.map(helper.getConnection().add(payment), LocalTransaction.class);
        System.out.println(localTransaction);
        transactionRepository.save(localTransaction);
        return new ResponseEntity<>(localTransaction, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getTransaction(String id) {
        LocalTransaction localTransaction= transactionRepository.findByTransactionId(id);
        return new ResponseEntity<>(modelMapper.map(localTransaction,LocalTransactionModel.class),HttpStatus.OK);
    }
}
