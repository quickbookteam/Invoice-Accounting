package com.accounting.service.Implimentation;

import com.accounting.entity.transaction.LocalTransaction;
import com.accounting.exception.CustomException;
import com.accounting.exception.TransactionNotFoundException;
import com.accounting.modal.CommonResponse;
import com.accounting.modal.transaction.LocalTransactionModel;
import com.accounting.repositery.TransactionRepository;
import com.accounting.service.TransactionServices;
import com.accounting.util.Helper;
import com.accounting.util.UtilConstants;
import com.intuit.ipp.data.Payment;

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

    public TransactionServiceImpl(@Qualifier("transactionRepository") TransactionRepository transactionRepository, @Qualifier("helper")Helper helper) {
        this.transactionRepository = transactionRepository;
        this.helper=helper;
        this.modelMapper =new ModelMapper();
    }

    @Override
    public ResponseEntity<?> saveTransaction(LocalTransactionModel localTransactionModel) {
        try{
            Payment payment = modelMapper.map(localTransactionModel, Payment.class);
            LocalTransaction localTransaction = modelMapper.map(helper.getConnection().add(payment), LocalTransaction.class);
            transactionRepository.save(localTransaction);
            CommonResponse response = new CommonResponse(localTransaction, UtilConstants.TRANSECTION_SAVED);
            return new ResponseEntity<CommonResponse>(response, HttpStatus.CREATED);
        }
        catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getTransaction(String id) {
        Optional<LocalTransaction> localTransaction= transactionRepository.findById(id);
        if(!localTransaction.isEmpty()){
            CommonResponse response = new CommonResponse(localTransaction, UtilConstants.TRANSECTION_FOUND);
            return new ResponseEntity<CommonResponse>(response,HttpStatus.FOUND);
        }
        throw  new TransactionNotFoundException(UtilConstants.TRANSACTION_NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> updateTransaction(String id, LocalTransactionModel localTransactionModel) {
        try {
            Payment payment = modelMapper.map(localTransactionModel, Payment.class);
            LocalTransaction localTransaction = modelMapper.map(helper.getConnection().update(payment), LocalTransaction.class);
            transactionRepository.save(localTransaction);
            CommonResponse response = new CommonResponse(localTransaction, UtilConstants.TRANSECTION_SAVED);
            return new ResponseEntity<CommonResponse>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }
}
