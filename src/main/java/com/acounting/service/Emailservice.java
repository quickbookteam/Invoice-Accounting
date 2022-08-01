package com.acounting.service;

import com.acounting.modal.EmailDetails;

public interface Emailservice {
	
	String sendSimpleMail(EmailDetails details);
	String sendMailWithAttachment(EmailDetails details);
}
