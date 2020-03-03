package com.gridstudentpractice.chatservice.security;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);

}
