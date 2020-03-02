package com.gridstudentpractice.chatservice.service;

import com.gridstudentpractice.chatservice.model.User;
import com.gridstudentpractice.chatservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbUserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.createUser(user);
    }

    @Override
    public User getUser(String userLogin) {
        return  userRepository.getUser(userLogin);
    }

    @Override
    public void editUser(User user, int id) {
        userRepository.updateUser(user, id);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteUser(id);
    }
}
