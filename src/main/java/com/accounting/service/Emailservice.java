package com.accounting.service;

import com.accounting.modal.EmailDetails;

public interface Emailservice {
	
	String sendSimpleMail(EmailDetails details);
	String sendMailWithAttachment(EmailDetails details);
}
