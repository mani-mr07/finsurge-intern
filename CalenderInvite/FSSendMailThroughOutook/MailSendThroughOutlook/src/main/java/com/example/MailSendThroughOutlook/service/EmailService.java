
//code for initial stage like send from outlook with ics file
//package com.example.MailSendThroughOutlook.service;
//
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.time.ZonedDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.UUID;
//
//@Service
//public class EmailService {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    public void sendCalendarInvites(List<String> recipients, String eventTitle, LocalDateTime startTime, LocalDateTime endTime) throws MessagingException, IOException {
//        File icsFile = generateICSFile(eventTitle, startTime, endTime);
//        String googleCalendarLink = generateGoogleCalendarLink(eventTitle, startTime, endTime);
//
//        for (String recipient : recipients) {
//            String domain = recipient.substring(recipient.indexOf("@") + 1);
//            boolean isRecipientGmail = domain.contains("gmail.com");
//
//            if (isRecipientGmail) {
//                // Send Google Calendar link
//                sendEmail(recipient, "Event Invitation", googleCalendarEmailBody(eventTitle, googleCalendarLink));
//            } else {
//                // Send ICS file for Outlook users
//                sendEmailWithICS(recipient, "Event Invitation", outlookEmailBody(eventTitle), icsFile);
//            }
//        }
//    }
//
//    private String generateGoogleCalendarLink(String eventTitle, LocalDateTime startTime, LocalDateTime endTime) {
//        ZonedDateTime startUTC = startTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));
//        ZonedDateTime endUTC = endTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
//
//        return "https://calendar.google.com/calendar/render?action=TEMPLATE"
//                + "&text=" + URLEncoder.encode(eventTitle, StandardCharsets.UTF_8)
//                + "&dates=" + startUTC.format(formatter) + "/" + endUTC.format(formatter)
//                + "&details=" + URLEncoder.encode("Event scheduled.", StandardCharsets.UTF_8);
//    }
//
//    // ✅ FIX: Ensure ICS times are in UTC to prevent 2-hour shifts
//    private File generateICSFile(String eventTitle, LocalDateTime startTime, LocalDateTime endTime) throws IOException {
//        ZonedDateTime startUTC = startTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));
//        ZonedDateTime endUTC = endTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
//
//        File file = File.createTempFile("event", ".ics");
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
//            writer.write("BEGIN:VCALENDAR\n");
//            writer.write("VERSION:2.0\n");
//            writer.write("PRODID:-//MyApp//NONSGML Event//EN\n");
//            writer.write("METHOD:REQUEST\n");
//            writer.write("BEGIN:VEVENT\n");
//            writer.write("UID:" + UUID.randomUUID().toString() + "\n");
//            writer.write("SUMMARY:" + eventTitle + "\n");
//            writer.write("DTSTART:" + startUTC.format(formatter) + "\n");
//            writer.write("DTEND:" + endUTC.format(formatter) + "\n");
//            writer.write("DESCRIPTION:This is a scheduled event.\n");
//            writer.write("STATUS:CONFIRMED\n");
//            writer.write("END:VEVENT\n");
//            writer.write("END:VCALENDAR\n");
//        }
//        return file;
//    }
//
//    private void sendEmail(String recipientEmail, String subject, String body) throws MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
//        helper.setFrom("noreply@finsurge.ai");
//        helper.setTo(recipientEmail);
//        helper.setSubject(subject);
//        helper.setText(body, true);
//
//        mailSender.send(message);
//    }
//
//    private void sendEmailWithICS(String recipientEmail, String subject, String body, File icsFile) throws MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
//        helper.setFrom("noreply@finsurge.ai");
//        helper.setTo(recipientEmail);
//        helper.setSubject(subject);
//        helper.setText(body, true);
//        helper.addAttachment("event.ics", icsFile);
//
//        mailSender.send(message);
//    }
//
//    private String googleCalendarEmailBody(String eventTitle, String googleCalendarLink) {
//        return "<p>Hello,</p>"
//                + "<p>You are invited to the event: <strong>" + eventTitle + "</strong></p>"
//                + "<p><a href='" + googleCalendarLink + "' target='_blank' style='background-color: #008CBA; color: white; padding: 10px 15px; text-decoration: none; border-radius: 5px;'>📅 Add to Google Calendar</a></p>";
//    }
//
//    private String outlookEmailBody(String eventTitle) {
//        return "<p>Hello,</p>"
//                + "<p>You are invited to the event: <strong>" + eventTitle + "</strong></p>"
//                + "<p>Please find the attached ICS file to add the event to your Outlook calendar.</p>";
//    }
//}




//Updated code like adding accept and reject button for outlook and gmail from outlook users
//package com.example.MailSendThroughOutlook.service;
//
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.InternetAddress;
//import jakarta.mail.internet.MimeBodyPart;
//import jakarta.mail.internet.MimeMessage;
//import jakarta.mail.internet.MimeMultipart;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//import java.io.UnsupportedEncodingException;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.UUID;
//
//@Service
//public class EmailService {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    public void sendCalendarInvite(List<String> recipients, String eventTitle, LocalDateTime startTime, LocalDateTime endTime)
//            throws MessagingException, UnsupportedEncodingException {
//
//        for (String recipient : recipients) {
//            sendCalendarInviteToUser(recipient, eventTitle, startTime, endTime);
//        }
//    }
//
//    private void sendCalendarInviteToUser(String recipientEmail, String eventTitle,
//                                          LocalDateTime startTime, LocalDateTime endTime)
//            throws MessagingException, UnsupportedEncodingException {
//
//        MimeMessage message = mailSender.createMimeMessage();
//
//        message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(recipientEmail));
//        message.setSubject("Event Invitation: " + eventTitle);
//        message.setFrom(new InternetAddress("noreply@yourdomain.com", "Event Organizer"));
//
//        String calendarContent = buildICSContent(eventTitle, startTime, endTime, recipientEmail);
//
//        MimeBodyPart htmlPart = new MimeBodyPart();
//        htmlPart.setContent("<p>You are invited to the event: <strong>" + eventTitle + "</strong></p>" +
//                "<p>Please check your calendar invite below and Accept or Decline.</p>", "text/html");
//
//        MimeBodyPart calendarPart = new MimeBodyPart();
//        calendarPart.addHeader("Content-Class", "urn:content-classes:calendarmessage");
//        calendarPart.setContent(calendarContent, "text/calendar;method=REQUEST;charset=UTF-8");
//
//        MimeMultipart multipart = new MimeMultipart("alternative");
//        multipart.addBodyPart(htmlPart);
//        multipart.addBodyPart(calendarPart);
//
//        message.setContent(multipart);
//
//        mailSender.send(message);
//    }
//
//    private String buildICSContent(String eventTitle, LocalDateTime startTime, LocalDateTime endTime, String attendee) {
//        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss");
//        String start = startTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).format(dtFormat);
//        String end = endTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).format(dtFormat);
//
//        return "BEGIN:VCALENDAR\n" +
//                "PRODID:-//Events Calendar//iCal4j 1.0//EN\n" +
//                "VERSION:2.0\n" +
//                "CALSCALE:GREGORIAN\n" +
//                "METHOD:REQUEST\n" +
//                "BEGIN:VEVENT\n" +
//                "UID:" + UUID.randomUUID().toString() + "\n" +
//                "DTSTAMP:" + LocalDateTime.now().format(dtFormat) + "Z\n" +
//                "DTSTART:" + start + "Z\n" +
//                "DTEND:" + end + "Z\n" +
//                "SUMMARY:" + eventTitle + "\n" +
//                "DESCRIPTION:This is a scheduled meeting\n" +
//                "LOCATION:Online\n" +
//                "ORGANIZER;CN=Event Organizer:mailto:noreply@yourdomain.com\n" +
//                "ATTENDEE;RSVP=TRUE;CN=" + attendee + ":mailto:" + attendee + "\n" +
//                "STATUS:CONFIRMED\n" +
//                "SEQUENCE:0\n" +
//                "TRANSP:OPAQUE\n" +
//                "END:VEVENT\n" +
//                "END:VCALENDAR";
//    }
//}

//Final code with all requirements like printing discription and title and all
package com.example.MailSendThroughOutlook.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendCalendarInvite(List<String> recipients, String eventTitle, String description,
                                   LocalDate eventDate, LocalTime startTime, LocalTime endTime)
            throws MessagingException, UnsupportedEncodingException {

        LocalDateTime startDateTime = LocalDateTime.of(eventDate, startTime);
        LocalDateTime endDateTime = LocalDateTime.of(eventDate, endTime);

        for (String recipient : recipients) {
            sendCalendarInviteToUser(recipient, eventTitle, description, startDateTime, endDateTime);
        }
    }

    private void sendCalendarInviteToUser(String recipientEmail, String eventTitle, String description,
                                          LocalDateTime startTime, LocalDateTime endTime)
            throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = mailSender.createMimeMessage();
        message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(recipientEmail));
        message.setSubject("Event Invitation: " + eventTitle);
        message.setFrom(new InternetAddress("noreply@finsurge.ai", "Event Organizer"));

        // Build calendar invite content
        String calendarContent = buildICSContent(eventTitle, description, startTime, endTime, recipientEmail);

        // Email content
        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(
                "<p><strong>Event:</strong> " + eventTitle + "</p>" +
                        "<p><strong>Date:</strong> " + startTime.toLocalDate() + "</p>" +
                        "<p><strong>Time:</strong> " + startTime.toLocalTime() + " - " + endTime.toLocalTime() + "</p>" +
                        "<p><strong>Description:</strong> " + description + "</p>" +
                        "<p>Please respond to this invite to accept or decline.</p>", "text/html");

        MimeBodyPart calendarPart = new MimeBodyPart();
        calendarPart.addHeader("Content-Class", "urn:content-classes:calendarmessage");
        calendarPart.setContent(calendarContent, "text/calendar;method=REQUEST;charset=UTF-8");

        MimeMultipart multipart = new MimeMultipart("alternative");
        multipart.addBodyPart(htmlPart);
        multipart.addBodyPart(calendarPart);

        message.setContent(multipart);

        mailSender.send(message);
    }

    private String buildICSContent(String eventTitle, String description,
                                   LocalDateTime startTime, LocalDateTime endTime, String attendee) {

        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss");
        String start = startTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).format(dtFormat);
        String end = endTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).format(dtFormat);

        return "BEGIN:VCALENDAR\n" +
                "PRODID:-//Events Calendar//iCal4j 1.0//EN\n" +
                "VERSION:2.0\n" +
                "CALSCALE:GREGORIAN\n" +
                "METHOD:REQUEST\n" +
                "BEGIN:VEVENT\n" +
                "UID:" + UUID.randomUUID() + "\n" +
                "DTSTAMP:" + LocalDateTime.now().format(dtFormat) + "Z\n" +
                "DTSTART:" + start + "Z\n" +
                "DTEND:" + end + "Z\n" +
                "SUMMARY:" + eventTitle + "\n" +
                "DESCRIPTION:" + description + "\n" +
                "LOCATION:Online\n" +
                "ORGANIZER;CN=Event Organizer:mailto:noreply@finsurge.ai\n" +
                "ATTENDEE;RSVP=TRUE;CN=" + attendee + ":mailto:" + attendee + "\n" +
                "STATUS:CONFIRMED\n" +
                "SEQUENCE:0\n" +
                "TRANSP:OPAQUE\n" +
                "END:VEVENT\n" +
                "END:VCALENDAR";
    }
}
