package com.acounting.exception;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvoiceNotFoundException extends RuntimeException {

	public InvoiceNotFoundException(String string) {
		super(string);
	}

}
