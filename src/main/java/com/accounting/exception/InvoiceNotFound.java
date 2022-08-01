package com.accounting.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvoiceNotFound extends RuntimeException {

	public InvoiceNotFound(String string)
	{
		super(string);
	}
}
