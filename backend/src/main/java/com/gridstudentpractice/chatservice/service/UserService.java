package com.gridstudentpractice.chatservice.service;

import com.gridstudentpractice.chatservice.model.User;

public interface UserService {

    boolean addUser(User user);
    User getUser(String loginToGet);

}
