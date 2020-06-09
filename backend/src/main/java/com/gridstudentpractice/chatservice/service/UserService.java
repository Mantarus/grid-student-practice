package com.gridstudentpractice.chatservice.service;

import com.gridstudentpractice.chatservice.model.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void addUser(UserDto userDto);
    UserDto getUserByLogin(String userLogin);
    void updateUserLoginAndPassword(UserDto userDto);
    void addRoleToUser(int rId, int uId);
    void deleteUserById(int id);

}
