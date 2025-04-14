package com.googleCalenderIntegration.googlecalenderIntegration.controller;

import com.googleCalenderIntegration.googlecalenderIntegration.entity.Email;
import com.googleCalenderIntegration.googlecalenderIntegration.service.EmailService;
import com.googleCalenderIntegration.googlecalenderIntegration.service.MicrosoftAuthService;
import jakarta.activation.DataHandler;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@RequestMapping("/send/email")
public class EmailController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private MicrosoftAuthService microsoftAuthService;
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    @PostMapping
    public ResponseEntity<?> sendMail(@RequestBody Email email) throws MessagingException {
        return new ResponseEntity<>(emailService.sendCalendarInvite(email.getRecipientEmail(),email.getEventTitle(),email.getEventLocation(),email.getStartTime(),email.getEndTime()), HttpStatus.OK);
    }

    @PostMapping("/send-invite")
    public String sendCalendarInvite(@RequestBody Email request) {
        return emailService.createOutlookEvent(
                request.getRecipientEmail(),
                request.getEventTitle(),
                request.getEventLocation(),
                request.getStartTime().atOffset(ZoneOffset.UTC),
                request.getEndTime().atOffset(ZoneOffset.UTC)
        );
    }
    @PostMapping("/sendMail")
    public void sendMail(@RequestParam String toEmail, @RequestParam String role, @RequestParam String body) throws Exception {


      emailService.sendCalendarInvite(toEmail,role);


//            MimeMessage message = mailSender.createMimeMessage();
//            message.setFrom(new InternetAddress(username));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
////            message.setSubject("Interview Scheduled - Please Accept the Invite");
//            message.setSentDate(new Date());
//
////        String dateStr = interviewStartTimeUTC.toLocalDate().toString(); // e.g. 2025-04-06
//        message.setSubject("Interview for software developer on ");
////        message.setSentDate(new Date());
//
//            // Plain text message
//            String textBody = "Hi,\n\nYou are invited to an interview. Please accept the calendar invite.\n\nRegards,\nManikandan E";
//
//            // Calendar body content
//            String calendarContent =
//                    "BEGIN:VCALENDAR\n" +
//                            "METHOD:REQUEST\n" +
//                            "PRODID:Microsoft Exchange Server 2010\n" +
//                            "VERSION:2.0\n" +
//                            "BEGIN:VEVENT\n" +
//                            "DTSTART:20250406T103000Z\n" +
//                            "DTEND:20250406T113000Z\n" +
//                            "DTSTAMP:" + getCurrentDateTime() + "\n" +
//                            "ORGANIZER;CN=Manikandan:mailto:" + username + "\n" +
//                            "UID:12345678\n" +
//                            "ATTENDEE;CN=Candidate;RSVP=TRUE:mailto:" + toEmail + "\n" +
//                            "DESCRIPTION:This is a calendar invite for the scheduled interview.\n" +
//                            "SUMMARY:Interview with Manikandan\n" +
//                            "LOCATION:Microsoft Teams / Office / GMeet\n" +
//                            "SEQUENCE:0\n" +
//                            "STATUS:CONFIRMED\n" +
//                            "TRANSP:OPAQUE\n" +
//                            "BEGIN:VALARM\n" +
//                            "TRIGGER:-PT15M\n" +
//                            "ACTION:DISPLAY\n" +
//                            "DESCRIPTION:Reminder\n" +
//                            "END:VALARM\n" +
//                            "END:VEVENT\n" +
//                            "END:VCALENDAR";
//
//            // Mime parts
//            MimeBodyPart textPart = new MimeBodyPart();
//            textPart.setText(textBody);
//
//            MimeBodyPart calendarPart = new MimeBodyPart();
//            calendarPart.setDataHandler(new DataHandler(
//                    new ByteArrayDataSource(calendarContent, "text/calendar;method=REQUEST;charset=UTF-8")));
//            calendarPart.setHeader("Content-Class", "urn:content-classes:calendarmessage");
//            calendarPart.setHeader("Content-ID", "calendar_message");
//
//            // Combine parts
//            Multipart multipart = new MimeMultipart();
//            multipart.addBodyPart(textPart);
//            multipart.addBodyPart(calendarPart);
//
//            message.setContent(multipart);
//
//            mailSender.send(message);
//            System.out.println("✅ Calendar invite sent successfully with Accept/Decline buttons");
    }

    private String getCurrentDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
        return ZonedDateTime.now(ZoneOffset.UTC).format(formatter);
    }

}
