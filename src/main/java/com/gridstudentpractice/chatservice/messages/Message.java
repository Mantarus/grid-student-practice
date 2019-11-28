package com.gridstudentpractice.chatservice.messages;

import java.time.LocalDateTime;

public class Message {

    private long mID;
    private String mSender;
    private String mBody;
    private LocalDateTime mTimestamp = LocalDateTime.now();

    public Message() {

    }

    public Message(long mID, String mSender, String mBody, LocalDateTime mTimestamp) {
        this.mID = mID;
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
        return mID;
    }

    public void setMID(long mID) {
        this.mID = mID;
    }

}
