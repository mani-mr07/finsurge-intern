package com.example.MailSendThroughOutlook.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class EmailRequest {
    private List<String> recipients;
    private String eventTitle;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    // Getters and Setters
    public List<String> getRecipients() { return recipients; }
    public void setRecipients(List<String> recipients) { this.recipients = recipients; }

    public String getEventTitle() { return eventTitle; }
    public void setEventTitle(String eventTitle) { this.eventTitle = eventTitle; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
}