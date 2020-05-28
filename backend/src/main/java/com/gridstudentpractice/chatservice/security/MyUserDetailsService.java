package com.gridstudentpractice.chatservice.security;

import com.gridstudentpractice.chatservice.model.UserDto;
import com.gridstudentpractice.chatservice.repository.UserRepository;
import com.gridstudentpractice.chatservice.repository.orm.ORMUserRepository;
import com.gridstudentpractice.chatservice.model.MyUserDetails;

import com.gridstudentpractice.chatservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserDto userDto = userRepository.getUserByLogin(userName);
        Optional<UserDto> optionalUserDto;

        if (userDto.getLogin() != null) {
            optionalUserDto = Optional.of(userDto);
        } else {
            throw new UsernameNotFoundException("Not found: " + userName);
        }

        System.out.printf("username = %s%nuserPass = %s%nuserRole = %s", userDto.getLogin(), userDto.getPassword(), userDto.getRoles());

        return optionalUserDto.map(MyUserDetails::new).get();
    }
}