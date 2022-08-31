package com.accounting.controller;

import com.accounting.modal.transaction.LocalTransactionModel;
import com.accounting.service.TransactionServices;
import com.intuit.ipp.exception.FMSException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@Slf4j
public class TransactionController {

    TransactionServices transactionServices;

    @Autowired
    public TransactionController(TransactionServices transactionServices) {
        this.transactionServices =transactionServices;
    }

    @PostMapping
    public ResponseEntity<?> saveTransaction(@RequestBody LocalTransactionModel localTransactionModel) throws FMSException {
    	log.info("inside save transation");
        return transactionServices.saveTransaction(localTransactionModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTransaction(@PathVariable("id") String id){
    	log.info("inside get transaction by id");
        return transactionServices.getTransaction(id);
    }
}
