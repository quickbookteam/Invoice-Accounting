package com.invoice_acounting.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomerException(String string,HttpStatus badRequest) {
		super(string);
	}

	

}
