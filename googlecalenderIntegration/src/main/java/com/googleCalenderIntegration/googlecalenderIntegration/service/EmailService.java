package com.googleCalenderIntegration.googlecalenderIntegration.service;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.googleCalenderIntegration.googlecalenderIntegration.entity.Email;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import jakarta.activation.DataHandler;
import jakarta.mail.Multipart;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import okhttp3.Request;
import com.microsoft.graph.requests.GraphServiceClient;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.microsoft.graph.models.*;
import org.springframework.web.client.RestTemplate;
import jakarta.mail.Message;



import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    private static final String CLIENT_ID = "7f4dac42-8f5d-437d-a402-24108e5af681";
    private static final String CLIENT_SECRET = "4ti8Q~ZNnYd~TIXLX1fhMKCtPMwMYtxnT1KbEbAv";
    private static final String TENANT_ID = "1eb2c39b-a5de-4852-ba7e-0ac6212856d5";

    @Value("${spring.mail.username}")
    private String senderEmail;
    @Autowired
    private MicrosoftAuthService authService;

    public String sendCalendarInvite(String recipientEmail, String eventTitle, String eventLocation,
                                     LocalDateTime startTime, LocalDateTime endTime) throws MessagingException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(recipientEmail);
        helper.setSubject("You're Invited: " + eventTitle);
        helper.setText("Please find the attached calendar invite for the event.");


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
        String startUtc = startTime.format(formatter);
        String endUtc = endTime.format(formatter);

        // Create .ics calendar event
        String calendarContent = "BEGIN:VCALENDAR\n" +
                "VERSION:2.0\n" +
                "PRODID:-//YourCompany//YourApp//EN\n" +
                "CALSCALE:GREGORIAN\n" +
                "BEGIN:VEVENT\n" +
                "UID:" + System.currentTimeMillis() + "@yourapp.com\n" +
                "SUMMARY:" + eventTitle + "\n" +
                "DTSTAMP:" + LocalDateTime.now().format(formatter) + "\n" +
                "DTSTART:" + startUtc + "\n" +
                "DTEND:" + endUtc + "\n" +
                "LOCATION:" + eventLocation + "\n" +
                "DESCRIPTION:You are invited to " + eventTitle + "\n" +
                "BEGIN:VALARM\n" +
                "TRIGGER:-PT10M\n" +  // 10 minutes before event
                "ACTION:DISPLAY\n" +
                "DESCRIPTION:Reminder for " + eventTitle + "\n" +
                "END:VALARM\n" +
                "END:VEVENT\n" +
                "END:VCALENDAR";

        // Convert string to input stream source
        InputStreamSource attachment = new ByteArrayResource(calendarContent.getBytes(StandardCharsets.UTF_8));

        // Attach calendar event
        helper.addAttachment("event.ics", attachment, "text/calendar");

        // Send email
        mailSender.send(message);
        return "mail is successfully sent";
    }

    private GraphServiceClient<Request> getGraphClient() {
        ClientSecretCredential credential = new ClientSecretCredentialBuilder()
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .tenantId(TENANT_ID)
                .build();

        List<String> scopes = List.of("https://graph.microsoft.com/.default");

        TokenCredentialAuthProvider authProvider = new TokenCredentialAuthProvider(scopes, credential);

        return GraphServiceClient.builder()
                .authenticationProvider(authProvider)
                .buildClient();
    }

    public String createOutlookEvent(String recipientEmail, String eventTitle, String eventLocation,
                                     OffsetDateTime startTime, OffsetDateTime endTime) {

        GraphServiceClient<Request> graphClient = authService.getGraphClient();


        Event event = new Event();
        event.subject = eventTitle;
        event.body = new ItemBody();
        event.body.contentType = BodyType.HTML;
        event.body.content = "You are invited to " + eventTitle;

        event.start = new DateTimeTimeZone();
        event.start.dateTime = startTime.toString();
        event.start.timeZone = "UTC";

        event.end = new DateTimeTimeZone();
        event.end.dateTime = endTime.toString();
        event.end.timeZone = "UTC";

        event.location = new Location();
        event.location.displayName = eventLocation;

        Attendee attendee = new Attendee();
        attendee.emailAddress = new EmailAddress();
        attendee.emailAddress.address = recipientEmail;
        attendee.type = AttendeeType.REQUIRED;

        event.attendees = new LinkedList<>();
        event.attendees.add(attendee);

        event.responseRequested = true; // Enables Accept/Decline options

        System.out.println("all the request except the userRequestBuilder are satisfied");
        Event createdEvent = graphClient
                .me()
                .events()
                .buildRequest()
                .post(event);
        return "Event Created: " + createdEvent.webLink;
    }

    @Value("${spring.mail.username}")
    private String username;
    private final RestTemplate restTemplate = new RestTemplate();
    public void sendCalendarInvite(String toEmail, String role) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        LocalDateTime ldt = LocalDateTime.of(2025, 4, 9, 10, 30); // 8th April 2025 at 10:30 AM
        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        Date startDate = Date.from(zdt.toInstant());        // Subject with date and role
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
        message.setSubject("Interview for " + role + " on " + formattedDate);
        message.setSentDate(new Date());

        Date stopDate=startDate;

        // Text message
        String textBody = "Hi,\n\nYou are invited to an interview for the role of " + role + ".\n"
                + "Please review the calendar invite and confirm your availability.\n\nRegards,\nManikandan E";



        // Calendar (ICS) content
        String calendarContent =
                "BEGIN:VCALENDAR\n" +
                        "METHOD:REQUEST\n" +
                        "PRODID:Microsoft Exchange Server 2010\n" +
                        "VERSION:2.0\n" +
                        "BEGIN:VEVENT\n" +
                        "DTSTART:" + formatICSDate(startDate) + "\n" +
                        "DTEND:" + formatICSDate(stopDate) + "\n" +
                        "DTSTAMP:" + formatICSDate(new Date()) + "\n" +
                        "ORGANIZER;CN=Manikandan:mailto:" + username + "\n" +
                        "UID:" + System.currentTimeMillis() + "@finsurge.ai\n" +
                        "ATTENDEE;CN=Candidate;RSVP=TRUE:mailto:" + toEmail + "\n" +
                        "DESCRIPTION:Interview for the role of " + role + ".\n"
                        + "Click Accept to confirm and add to your calendar.\n"
                        + "Google Meet Link: https://meet.google.com/xyz-1234-abc\n" +
                        "SUMMARY:Interview with Manikandan\n" +
                        "LOCATION:Google Meet / Office\n" +
                        "SEQUENCE:0\n" +
                        "STATUS:CONFIRMED\n" +
                        "TRANSP:OPAQUE\n" +
                        "BEGIN:VALARM\n" +
                        "TRIGGER:-PT15M\n" +     // Reminder 15 minutes before
                        "ACTION:DISPLAY\n" +
                        "DESCRIPTION:Reminder\n" +
                        "END:VALARM\n" +
                        "END:VEVENT\n" +
                        "END:VCALENDAR";

        // Text body part
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText(textBody);


        // Calendar body part
        MimeBodyPart calendarPart = new MimeBodyPart();
        calendarPart.setDataHandler(new DataHandler(
                new ByteArrayDataSource(calendarContent, "text/calendar;method=REQUEST;charset=UTF-8")));
        calendarPart.setHeader("Content-Class", "urn:content-classes:calendarmessage");
        calendarPart.setHeader("Content-ID", "calendar_message");

        // Combine parts
        Multipart multipart = new MimeMultipart();

        multipart.addBodyPart(textPart);
        multipart.addBodyPart(calendarPart);
        message.setContent(multipart);

        // Send the email
        mailSender.send(message);
        System.out.println(" Calendar invite sent successfully with Accept/Decline buttons");
    }
    private String googleCalendarEmailBody(String eventTitle, String googleCalendarLink) {
        return "<p>Hello,</p>"+ "<p>You are invited to the event: <strong>" + eventTitle + "</strong></p>"      + "<p><a href='" + googleCalendarLink + "' target='_blank' style='background-color: #008CBA; color: white; padding: 10px 15px; text-decoration: none; border-radius: 5px;'>📅 Add to Google Calendar</a></p>";}

    private String formatICSDate(Date date) {
        SimpleDateFormat icsFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
        icsFormat.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
        return icsFormat.format(date);
    }




}


