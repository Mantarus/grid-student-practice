package com.gridstudentpractice.chatservice.model;

import javax.validation.constraints.NotBlank;

public class User {

    private int id;
    @NotBlank
    private String login;
    @NotBlank
    private String password;

    public User() {}

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public long getID() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String uLogin) {
        this.login = uLogin;
    }

    public void setPass(String uPass) {
        this.password = uPass;
    }
}
