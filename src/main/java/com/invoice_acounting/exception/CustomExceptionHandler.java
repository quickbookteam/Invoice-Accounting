package com.invoice_acounting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.invoice_acounting.modal.CommonResponse;
import com.invoice_acounting.util.UtilConstants;

@ControllerAdvice
public class CustomExceptionHandler {

	  @ExceptionHandler(value = CustomerException.class)
	   public ResponseEntity<CommonResponse> customerException(String Message,HttpStatus status) {
		  CommonResponse response = new CommonResponse(null, Message);
	      return new ResponseEntity<>(response, status);
	   }
		@ExceptionHandler(value = InvoiceException.class)
		public  ResponseEntity<CommonResponse> invoiceException(String Message,HttpStatus status) {
			 CommonResponse response = new CommonResponse(null, Message);
		      return new ResponseEntity<>(response, status);
		}

//	@ExceptionHandler(value = CustomerException.class)
//	public c ResponseEntity<CommonResponse> exception(CustomerException exception) {
//		return new ResponseEntity<>(UtilContants.CUSTOMER_NOT_FOUND, HttpStatus.NOT_FOUND);
//	}


}
