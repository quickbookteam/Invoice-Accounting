package com.invoice_acounting.service.Implimentation;

import com.intuit.ipp.data.Payment;
import com.intuit.ipp.exception.FMSException;
import com.invoice_acounting.entity.transaction.LocalTransaction;
import com.invoice_acounting.modal.transaction.LocalTransactionModel;
import com.invoice_acounting.repositery.TransactionRepository;
import com.invoice_acounting.service.TransactionServices;
import com.invoice_acounting.util.Helper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Qualifier("transactionServiceImplementation")
public class TransactionServiceImpl implements TransactionServices {

    private TransactionRepository transactionRepository;

    private Helper helper;

    private ModelMapper modelMapper;

    @Autowired
    MongoTemplate mongoTemplate;

    public TransactionServiceImpl(@Qualifier("transactionRepository") TransactionRepository transactionRepository, @Qualifier("helper")Helper helper) {
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
