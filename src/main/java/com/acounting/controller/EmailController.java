package com.acounting.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.acounting.modal.EmailDetails;
import com.acounting.service.Emailservice;

// Annotation
@RestController
// Class
public class EmailController {

    @Autowired
    private Emailservice emailService;
    
	

    @PostMapping("/hello")
    public String sendmail(@RequestBody EmailDetails details)
    {
        String status = emailService.sendSimpleMail(details);
        return status;
    }
    // Sending email with attachment
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(
            @RequestBody EmailDetails details)
    {
        String status= emailService.sendMailWithAttachment(details);

        return status;
    }
    
 
}