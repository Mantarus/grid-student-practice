package com.gridstudentpractice.chatservice.repository;

import com.gridstudentpractice.chatservice.model.User;

public interface UserRepository {

    void createUser(User user);
    User getUser(String userLogin);

}
