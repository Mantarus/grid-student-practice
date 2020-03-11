package com.gridstudentpractice.chatservice.repository;

import com.gridstudentpractice.chatservice.model.User;

public interface UserRepository {

    void createUser(User user);
    User getUserByLogin(String userLogin);
    void updateUser(User user);
    void deleteUserById(int id);

}
