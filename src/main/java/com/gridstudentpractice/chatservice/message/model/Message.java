package com.gridstudentpractice.chatservice.message.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class Message {

    private long mId;
    private static AtomicLong mIdCounter = new AtomicLong(0);
    private String mSender;
    private String mBody;
    private LocalDateTime mTimestamp = LocalDateTime.now();

    public Message() {

    }

    public Message(String mSender, String mBody, LocalDateTime mTimestamp) {
        this();
        this.mId = mIdCounter.incrementAndGet();
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
