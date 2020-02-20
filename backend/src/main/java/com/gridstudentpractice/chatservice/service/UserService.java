package com.gridstudentpractice.chatservice.service;

import com.gridstudentpractice.chatservice.model.User;

public interface UserService {

    void addUser(User user);
    User getUser(String userLogin);

}
