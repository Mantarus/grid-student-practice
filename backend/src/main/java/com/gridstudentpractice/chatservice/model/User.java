package com.gridstudentpractice.chatservice.model;

import javax.validation.constraints.NotBlank;
import java.util.concurrent.atomic.AtomicLong;

public class User {
    private long uId;
    private static AtomicLong uIdCounter = new AtomicLong(0);
    @NotBlank
    private String uLogin;
    @NotBlank
    private String uPass;

    public User() {
        this.uId = uIdCounter.incrementAndGet();
    }

    public User(String uLogin, String uPass) {
        this();
        this.uLogin = uLogin;
        this.uPass = uPass;
    }

    public String getULogin() {
        return uLogin;
    }

    public String getUPass() {
        return uPass;
    }

    public long getUID() {
        return uId;
    }

    public void setULogin(String uLogin) {
        this.uLogin = uLogin;
    }

    public void setUPass(String uPass) {
        this.uPass = uPass;
    }
}
