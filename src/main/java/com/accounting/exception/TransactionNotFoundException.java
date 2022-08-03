package com.accounting.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransactionNotFoundException extends RuntimeException {

    public TransactionNotFoundException(String string) {
        super(string);
    }

}
