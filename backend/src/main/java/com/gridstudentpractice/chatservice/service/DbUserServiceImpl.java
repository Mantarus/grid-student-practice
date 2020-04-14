package com.gridstudentpractice.chatservice.service;

import com.gridstudentpractice.chatservice.model.UserDto;
import com.gridstudentpractice.chatservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DbUserServiceImpl implements UserService {

    @Qualifier("ORMUserRepositoryImpl")
    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(UserDto userDto) {
        userRepository.createUser(userDto);
    }

    @Override
    public UserDto getUserByLogin(String userLogin) {
        return  userRepository.getUserByLogin(userLogin);
    }

    @Override
    public void updateUser(UserDto userDto) {
        userRepository.updateUser(userDto);
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteUserById(id);
    }
}
