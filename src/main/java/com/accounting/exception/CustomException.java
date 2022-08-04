package com.accounting.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public CustomException(String string)
	{
		super(string);
	}



}