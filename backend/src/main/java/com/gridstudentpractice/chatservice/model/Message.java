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

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
