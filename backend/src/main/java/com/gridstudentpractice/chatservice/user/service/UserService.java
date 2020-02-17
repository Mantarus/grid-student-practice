package com.gridstudentpractice.chatservice.user.service;

import com.gridstudentpractice.chatservice.user.model.User;

public interface UserService {

    User addUser(User user);
    boolean checkUser(User user);

}
