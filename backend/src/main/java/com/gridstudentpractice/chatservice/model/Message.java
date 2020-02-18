package com.gridstudentpractice.chatservice.model;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class Message {

    private int id;
    @NotBlank
    private String sender;
    @NotBlank
    private String body;
    private LocalDateTime timestamp = LocalDateTime.now();

    public Message() {}

    public Message(String sender, String body, LocalDateTime timestamp) {
        this.sender = sender;
        this.body = body;
        this.timestamp = timestamp;
    }

    public String getSender() {
        return sender;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setSender(String mSender) {
        this.sender = mSender;
    }

    public void setBody(String mBody) {
        this.body = mBody;
    }

    public void setTimestamp(LocalDateTime mTimestamp) {
        this.timestamp = mTimestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
