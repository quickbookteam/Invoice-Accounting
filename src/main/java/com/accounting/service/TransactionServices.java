package com.accounting.service;

import org.springframework.http.ResponseEntity;

import com.accounting.modal.CommonResponse;
import com.accounting.modal.transaction.LocalTransactionModel;
import com.intuit.ipp.exception.FMSException;

public interface TransactionServices {

	ResponseEntity<CommonResponse> saveTransaction(LocalTransactionModel localTransactionModel) throws FMSException;

	ResponseEntity<CommonResponse> getTransaction(String id);
}
