package com.accounting.service;

import com.accounting.modal.transaction.LocalTransactionModel;
import com.intuit.ipp.exception.FMSException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;

public interface TransactionServices {

    ResponseEntity<?> saveTransaction(LocalTransactionModel localTransactionModel) throws FMSException;

    ResponseEntity<?> getTransaction(String id);
}
