package com.accounting.exception;

public class CustomFileNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CustomFileNotFoundException(String string)
	{
		super(string);
	}

}
