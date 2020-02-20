package com.gridstudentpractice.chatservice.repository;

import com.gridstudentpractice.chatservice.model.User;

public interface UserRepository {

    boolean createUser(User user);
    User getUser(String userLogin);

}
