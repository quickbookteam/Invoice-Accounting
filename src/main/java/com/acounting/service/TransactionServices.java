package com.acounting.service;

import com.acounting.modal.transaction.LocalTransactionModel;
import com.intuit.ipp.exception.FMSException;

import org.springframework.http.ResponseEntity;

public interface TransactionServices {

    ResponseEntity<?> saveTransaction(LocalTransactionModel localTransactionModel) throws FMSException;

    ResponseEntity<?> getTransaction(String id);
}
