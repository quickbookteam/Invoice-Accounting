package com.invoice_acounting.controller;

import com.intuit.ipp.exception.FMSException;
import com.invoice_acounting.modal.transaction.LocalTransactionModel;
import com.invoice_acounting.service.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    TransactionServices transactionServices;

    @Autowired
    public TransactionController(@Qualifier("transactionServiceImplementation")TransactionServices transactionServices) {
        this.transactionServices =transactionServices;
    }

    @PostMapping
    public ResponseEntity<?> saveTransaction(@RequestBody LocalTransactionModel localTransactionModel) throws FMSException {
        return transactionServices.saveTransaction(localTransactionModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTransaction(@PathVariable("id") String id){
        return transactionServices.getTransaction(id);
    }
}
