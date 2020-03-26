package com.gridstudentpractice.chatservice.repository;

import com.gridstudentpractice.chatservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ORMUserRepositoryImpl implements UserRepository {

    @Autowired
    ORMUserRepository ormUserRepository;

    @Override
    public void createUser(User user) {

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
