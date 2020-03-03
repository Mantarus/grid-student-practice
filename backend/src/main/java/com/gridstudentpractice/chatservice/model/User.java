package com.gridstudentpractice.chatservice.model;

import javax.validation.constraints.NotBlank;

public class User {

    private int id;
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    private String role;

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

    public long getId() {
        return id;
    }

    public String getRole() { return this.role; }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String uLogin) {
        this.login = uLogin;
    }

    public void setPass(String uPass) {
        this.password = uPass;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
