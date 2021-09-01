package com.thecommunity.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {

    private final String senderEmail;
    private final String message;
    private final LocalDateTime timestamp;
    private final boolean isRead;

    public Message(String senderEmail, String message) {
        this.senderEmail = senderEmail;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.isRead = false;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String message = senderEmail + ": \n" + this.message + "\n\t\t\t\t" +
                timestamp.format(formatter);
        return message;
    }
}
