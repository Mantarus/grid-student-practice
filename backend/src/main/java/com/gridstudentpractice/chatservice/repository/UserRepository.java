package com.gridstudentpractice.chatservice.repository;

import com.gridstudentpractice.chatservice.model.UserDto;

public interface UserRepository {

    void createUser(UserDto userDto);
    UserDto getUserByLogin(String userLogin);
    void updateUser(UserDto userDto);
    void deleteUserById(int id);

}
