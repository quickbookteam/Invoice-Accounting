package com.invoice_acounting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.invoice_acounting.util.UtilContants;

@ControllerAdvice
public class CustomExceptionHandler {

//	  @ExceptionHandler(value = CustomerNotFoundException.class)
//	   public ResponseEntity<Object> exception(CustomerNotFoundException exception) {
//	      return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
//	   }

	@ExceptionHandler(value = CustomerNotFoundException.class)
	public ResponseEntity<Object> exception(CustomerNotFoundException exception) {
		return new ResponseEntity<>(UtilContants.CUSTOMER_NOT_FOUND, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = InvoiceNotFoundException.class)
	public ResponseEntity<Object> exception(InvoiceNotFoundException exception) {
		return new ResponseEntity<>(UtilContants.INVOICE_NOT_FOUND, HttpStatus.NOT_FOUND);
	}

}
