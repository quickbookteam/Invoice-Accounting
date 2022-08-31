package com.accounting.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConnectionNotFoundException extends RuntimeException {

	public ConnectionNotFoundException(String string) {
		super(string);
	}

}
