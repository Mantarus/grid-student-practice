package com.gridstudentpractice.chatservice.repository;

import com.gridstudentpractice.chatservice.model.UserDto;

public interface UserRepository {

    void createUser(UserDto userDto);
    UserDto getUserByLogin(String userLogin);
    void updateUserLoginAndPassword(UserDto userDto);
    void updateUserRole(UserDto userDto);
    void deleteUserById(int id);

}
