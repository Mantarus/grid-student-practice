package com.gridstudentpractice.chatservice.security;

import com.gridstudentpractice.chatservice.model.User;
import com.gridstudentpractice.chatservice.service.DbUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    DbUserServiceImpl dbUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = dbUserService.getUser(username);

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), user.getRole());
    }
}
