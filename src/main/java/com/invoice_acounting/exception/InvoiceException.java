package com.invoice_acounting.exception;


import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvoiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvoiceException(String string,HttpStatus status) {
		super(string);
	}

}
