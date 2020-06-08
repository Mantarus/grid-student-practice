package com.gridstudentpractice.chatservice.service;

import com.gridstudentpractice.chatservice.model.RoleDto;
import com.gridstudentpractice.chatservice.model.UserDto;
import com.gridstudentpractice.chatservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
    public void updateUserRole(UserDto userDto) {
        userRepository.updateUserRole(userDto);
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteUserById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDto userDto = userRepository.getUserByLogin(s);
        if (userDto == null) {
            throw new UsernameNotFoundException("Invalid login or password");
        }
        return null;
//        return new User(userDto.getLogin(),userDto.getPassword(), mapRoleToAuthority(userDto.getRole()));
    }

//    private GrantedAuthority mapRoleToAuthority(String role) {
//        return new SimpleGrantedAuthority(role);
//    }
}
