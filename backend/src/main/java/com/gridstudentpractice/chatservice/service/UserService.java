package com.gridstudentpractice.chatservice.service;

import com.gridstudentpractice.chatservice.model.UserDto;

public interface UserService {

    void addUser(UserDto userDto);
    UserDto getUserByLogin(String userLogin);
    void updateUser(UserDto userDto);
    void deleteUserById(int id);

}
