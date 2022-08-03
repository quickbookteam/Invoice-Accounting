package com.accounting.controller;

import com.accounting.modal.transaction.LocalTransactionModel;
import com.accounting.service.TransactionServices;
import com.intuit.ipp.exception.FMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/transaction")
@Slf4j
public class TransactionController {

    TransactionServices transactionServices;

    @Autowired
    public TransactionController(@Qualifier("transactionServiceImplementation")TransactionServices transactionServices) {
        this.transactionServices =transactionServices;
    }

    @PostMapping
    public ResponseEntity<?> saveTransaction(@RequestBody LocalTransactionModel localTransactionModel) throws FMSException {
        log.info("inside save transaction");
        log.info("transaction details", localTransactionModel);
        return transactionServices.saveTransaction(localTransactionModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTransaction(@PathVariable("id") String id){
        log.info("inside get transaction detail by id");
        return transactionServices.getTransaction(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTransaction(@PathVariable("id")String id,@RequestBody LocalTransactionModel localTransactionModel){
        log.info("inside update transaction by id ");
        log.info("updated transaction details", localTransactionModel);
        return transactionServices.updateTransaction(id,localTransactionModel);
    }
}
