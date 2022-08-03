package com.accounting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.accounting.modal.CommonResponse;
import com.accounting.scheduler.EmailSchedular;
import com.intuit.ipp.exception.FMSException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {
	@ExceptionHandler(InvoiceNotFoundException.class)
	public ResponseEntity<CommonResponse> invoiceNotFoundException(InvoiceNotFoundException obj) {
		CommonResponse response = new CommonResponse(null, obj.getMessage());
		return new ResponseEntity<CommonResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<CommonResponse> customerNotFoundException(CustomerNotFoundException obj) {
		CommonResponse response = new CommonResponse(null, obj.getMessage());
		return new ResponseEntity<CommonResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<CommonResponse> customException(CustomException obj) {
		CommonResponse response = new CommonResponse(null, obj.getMessage());
		return new ResponseEntity<CommonResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CustomFileNotFoundException.class)
	public void customFileNotFoundException(CustomFileNotFoundException obj) {
		log.info(obj.getMessage());

	}

}