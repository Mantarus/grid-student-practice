package com.gridstudentpractice.chatservice.service;

import com.gridstudentpractice.chatservice.model.UserDto;

public interface UserService {

    void addUser(UserDto userDto);
    UserDto getUserByLogin(String userLogin);
    void updateUserLoginAndPassword(UserDto userDto);
    void updateUserRole(UserDto userDto);
    void deleteUserById(int id);

}
