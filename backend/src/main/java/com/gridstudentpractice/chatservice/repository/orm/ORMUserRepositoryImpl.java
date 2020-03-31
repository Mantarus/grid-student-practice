package com.gridstudentpractice.chatservice.repository.orm;

import com.gridstudentpractice.chatservice.mapper.UserMapper;
import com.gridstudentpractice.chatservice.model.User;
import com.gridstudentpractice.chatservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository
public class ORMUserRepositoryImpl implements UserRepository {

    @Lazy
    @Autowired
    ORMUserRepository ormUserRepository;

    @Override
    public void createUser(User user) {
        ormUserRepository.save(UserMapper.INSTANCE.toEntity(user));
    }

    @Override
    public User getUserByLogin(String userLogin) {
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUserById(int id) {

    }
}
