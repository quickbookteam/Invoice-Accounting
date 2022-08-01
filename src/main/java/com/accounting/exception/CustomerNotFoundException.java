package com.accounting.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException(String string) {
		super(string);
	}

}
