package com.gridstudentpractice.chatservice.service;

import com.gridstudentpractice.chatservice.model.UserDto;
import com.gridstudentpractice.chatservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class DbUserServiceImpl implements UserService {

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
    public void updateUserLoginAndPassword(UserDto userDto) {
        userRepository.updateUserLoginAndPassword(userDto);
    }

    @Override
    public void addRoleToUser(int rId, int uId) {
        userRepository.addRoleToUser(rId, uId);
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteUserById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDto userDto = userRepository.getUserByLogin(s);

        if (userDto.getLogin() == null) {
            throw new UsernameNotFoundException("Not found: " + s);
        }

        return new User(userDto.getLogin(), userDto.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

}
