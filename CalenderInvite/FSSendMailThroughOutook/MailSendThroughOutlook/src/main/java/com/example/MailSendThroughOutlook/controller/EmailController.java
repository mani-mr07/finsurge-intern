package com.example.MailSendThroughOutlook.controller;

import com.example.MailSendThroughOutlook.DTO.EmailRequest;
import com.example.MailSendThroughOutlook.DTO.EmailRequestDTOWithIndividualDateAndTime;
import com.example.MailSendThroughOutlook.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.PublicKey;

@RestController
@RequestMapping("/api/emailoutlook")
public class EmailController {

    @Autowired
    private EmailService emailService;


    @PostMapping("/finalSend")
    public ResponseEntity<String> sendCalenderInvitesToAll(@RequestBody EmailRequestDTOWithIndividualDateAndTime emailRequestDTOWithIndividualDateAndTime) throws MessagingException, UnsupportedEncodingException {
        emailService.sendCalendarInvite(emailRequestDTOWithIndividualDateAndTime.getRecipients(),
                emailRequestDTOWithIndividualDateAndTime.getEventTitle(),
                emailRequestDTOWithIndividualDateAndTime.getEventDiscription(),
                emailRequestDTOWithIndividualDateAndTime.getLocalDate(),
                emailRequestDTOWithIndividualDateAndTime.getStartTime(),
                emailRequestDTOWithIndividualDateAndTime.getEndTime());
        return ResponseEntity.ok("Email Send Successfully");
    }
}

