package com.accounting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.accounting.modal.CommonResponse;

@RestControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(InvoiceNotFound.class)
	public ResponseEntity<CommonResponse> invoiceNotFound(InvoiceNotFound obj)
	{
		CommonResponse response=new CommonResponse(null,obj.getMessage());
		return new ResponseEntity<CommonResponse>(response,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(CustomerNotFound.class)
	public ResponseEntity<CommonResponse> customerNotFound(CustomerNotFound obj)
	{
		CommonResponse response=new CommonResponse(null,obj.getMessage());
		return new ResponseEntity<CommonResponse>(response,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<CommonResponse> customException(CustomException obj)
	{
		CommonResponse response=new CommonResponse(null,obj.getMessage());
		return new ResponseEntity<CommonResponse>(response,HttpStatus.NOT_FOUND);
	}
}
