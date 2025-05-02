package com.tradeport.dbwriterservice.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationTO {

    @JsonProperty("NotificationID")  // Maps "NotificationID" from JSON to notificationID field
    private UUID notificationID;

    @JsonProperty("UserID")
    private UUID userID;

    @JsonProperty("Subject")
    private String subject;

    @JsonProperty("Message")
    private String message;

    @JsonProperty("CreatedOn")
    private Date createdOn;

    @JsonProperty("CreatedBy")
    private UUID createdBy;

    @JsonProperty("EmailSend")
    private boolean emailSend;

    @JsonProperty("FromEmail")
    private String fromEmail;

    @JsonProperty("RecipientEmail")
    private String recipientEmail;

    @JsonProperty("FailureReason")
    private String failureReason;

    @JsonProperty("SentTime")
    private Date sentTime;

    // Default Constructor
    public NotificationTO() {}

    // Parameterized Constructor
    public NotificationTO(
            UUID notificationID,
            UUID userID,
            String subject,
            String message,
            Date createdOn,
            UUID createdBy,
            boolean emailSend,
            String fromEmail,
            String recipientEmail,
            String failureReason,
            Date sentTime) {
        this.notificationID = notificationID;
        this.userID = userID;
        this.subject = subject;
        this.message = message;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.emailSend = emailSend;
        this.fromEmail = fromEmail;
        this.recipientEmail = recipientEmail;
        this.failureReason = failureReason;
        this.sentTime = sentTime;
    }

    // Getters and Setters
    public UUID getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(UUID notificationID) {
        this.notificationID = notificationID;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
    }

    public boolean isEmailSend() {
        return emailSend;
    }

    public void setEmailSend(boolean emailSend) {
        this.emailSend = emailSend;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    @Override
    public String toString() {
        return "NotificationTO{" +
                "notificationID=" + notificationID +
                ", userID=" + userID +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", createdOn=" + createdOn +
                ", createdBy=" + createdBy +
                ", emailSend=" + emailSend +
                ", fromEmail='" + fromEmail + '\'' +
                ", recipientEmail='" + recipientEmail + '\'' +
                ", failureReason='" + failureReason + '\'' +
                ", sentTime=" + sentTime +
                '}';
    }
}
