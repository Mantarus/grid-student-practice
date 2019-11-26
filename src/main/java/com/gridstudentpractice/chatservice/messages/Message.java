package com.gridstudentpractice.chatservice.messages;

import java.util.Date;

public class Message {

    private long mId;
    private String mSender;
    private String mBody;
    private Date mTimestamp;

    public Message(long mId, String mSender, String mBody, Date mTimestamp) {
        this.mId = mId;
        this.mSender = mSender;
        this.mBody = mBody;
        this.mTimestamp = mTimestamp;
    }

    public long getMId() {
        return mId;
    }

    public String getMSender() {
        return mSender;
    }

    public String getMBody() {
        return mBody;
    }

    public Date getMTimestamp() {
        return mTimestamp;
    }

    public void setMId(long mId) {
        this.mId = mId;
    }

    public void setMSender(String mSender) {
        this.mSender = mSender;
    }

    public void setMBody(String mBody) {
        this.mBody = mBody;
    }

    public void setMTimestamp(Date mTimestamp) {
        this.mTimestamp = mTimestamp;
    }

}
