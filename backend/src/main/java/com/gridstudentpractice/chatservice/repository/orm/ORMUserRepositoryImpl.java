package com.gridstudentpractice.chatservice.repository.orm;

import com.gridstudentpractice.chatservice.exception.RepositoryException;
import com.gridstudentpractice.chatservice.mapper.UserMapper;
import com.gridstudentpractice.chatservice.model.Role;
import com.gridstudentpractice.chatservice.model.User;
import com.gridstudentpractice.chatservice.model.UserDto;
import com.gridstudentpractice.chatservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("orm")
@Repository
public class ORMUserRepositoryImpl implements UserRepository {

    @Lazy
    @Autowired
    private ORMUserRepository ormUserRepository;

    @Autowired
    private UserMapper mapper;

    @Override
    public void createUser(UserDto userDto) {
        ormUserRepository.save(mapper.toEntity(userDto));
    }

    @Override
    public UserDto getUserByLogin(String userLogin) {
        return mapper.toDTO(ormUserRepository.findByLogin(userLogin) );
    }

    @Override
    public void updateUserLoginAndPassword(UserDto userDto) {
        User user = ormUserRepository.findById(userDto.getId())
                .orElseThrow(() -> new RepositoryException("No such user found with id " + userDto.getId()));
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        ormUserRepository.save(user);
    }

    @Override
    public void updateUserRole(UserDto userDto) {
        User user = ormUserRepository.findById(userDto.getId())
                .orElseThrow(() -> new RepositoryException("No such user found with id " + userDto.getId()));
        Role role = new Role();
        role.setId(Integer.parseInt(userDto.getRole()));
        user.setRole(role);
        ormUserRepository.save(user);
    }

    @Override
    public void deleteUserById(int id) {
        ormUserRepository.deleteById(id);
    }
}
