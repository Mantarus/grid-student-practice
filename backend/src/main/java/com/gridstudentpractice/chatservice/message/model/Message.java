package com.gridstudentpractice.chatservice.message.model;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class Message {

    private long mId;
    private static AtomicLong mIdCounter = new AtomicLong(0);
    @NotBlank
    private String mSender;
    @NotBlank
    private String mBody;
    private LocalDateTime mTimestamp = LocalDateTime.now();

    public Message() {
        this.mId = mIdCounter.incrementAndGet();
    }

    public Message(String mSender, String mBody, LocalDateTime mTimestamp) {
        this();
        this.mSender = mSender;
        this.mBody = mBody;
        this.mTimestamp = mTimestamp;
    }

    public String getMSender() {
        return mSender;
    }

    public String getMBody() {
        return mBody;
    }

    public LocalDateTime getMTimestamp() {
        return mTimestamp;
    }

    public void setMSender(String mSender) {
        this.mSender = mSender;
    }

    public void setMBody(String mBody) {
        this.mBody = mBody;
    }

    public void setMTimestamp(LocalDateTime mTimestamp) {
        this.mTimestamp = mTimestamp;
    }

    public long getMID() {
        return mId;
    }

}
