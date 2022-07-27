package com.invoice_acounting.exception;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvoiceNotFoundException extends RuntimeException {

	public InvoiceNotFoundException(String string) {
		super(string);
	}

}
