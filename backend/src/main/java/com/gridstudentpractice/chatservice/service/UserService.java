package com.gridstudentpractice.chatservice.service;

import com.gridstudentpractice.chatservice.model.User;

public interface UserService {

    User addUser(User user);
    boolean checkUser(User user);

}
