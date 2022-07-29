package com.invoice_acounting.service;

import com.invoice_acounting.modal.EmailDetails;

public interface Emailservice {
	
	String sendSimpleMail(EmailDetails details);
	String sendMailWithAttachment(EmailDetails details);
}