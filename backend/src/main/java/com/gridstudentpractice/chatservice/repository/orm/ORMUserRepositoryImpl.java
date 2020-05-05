package com.gridstudentpractice.chatservice.repository.orm;

import com.gridstudentpractice.chatservice.mapper.UserMapper;
import com.gridstudentpractice.chatservice.model.UserDto;
import com.gridstudentpractice.chatservice.model.User;
import com.gridstudentpractice.chatservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
        return mapper.toDTO(ormUserRepository.findByLogin(userLogin));
    }

    @Override
    public void updateUser(UserDto userDto) {
        ormUserRepository.save(mapper.toEntity(userDto));
    }

    @Override
    public void deleteUserById(int id) {
        ormUserRepository.deleteById(id);
    }
}
