package com.gridstudentpractice.chatservice.service;

import com.gridstudentpractice.chatservice.model.User;

public interface UserService {

    void addUser(User user);
    User getUserByLogin(String userLogin);
    void updateUser(User user);
    void deleteUserById(int id);

}
