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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DbUserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void addUser(UserDto userDto) {
        userRepository.createUser(
                UserDto.builder()
                        .id(userDto.getId())
                        .login(userDto.getLogin())
                        .password(passwordEncoder.encode(userDto.getPassword()))
                        .build()
        );
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
        List<RoleDto> roleDtos = userRepository.getUserRoles(s);

        if (userDto.getLogin() == null) {
            throw new UsernameNotFoundException("Not found: " + s);
        }

        User user = new User(
                userDto.getLogin(),
                userDto.getPassword(),
                mapRolesToAuthorities(roleDtos)
        );
        System.out.println(user.getPassword());
        return user;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RoleDto> roleDtos) {
        return roleDtos.stream()
                .map(roleDto -> new SimpleGrantedAuthority(roleDto.getName()))
                .collect(Collectors.toList());
    }

}
