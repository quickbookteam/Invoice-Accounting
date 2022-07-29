package com.invoice_acounting.service;

import com.intuit.ipp.exception.FMSException;
import com.invoice_acounting.modal.transaction.LocalTransactionModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;

public interface TransactionServices {

    ResponseEntity<?> saveTransaction(LocalTransactionModel localTransactionModel) throws FMSException;

    ResponseEntity<?> getTransaction(String id);
}
