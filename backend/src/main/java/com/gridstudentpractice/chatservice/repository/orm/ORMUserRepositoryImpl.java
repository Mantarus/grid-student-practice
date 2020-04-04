package com.gridstudentpractice.chatservice.repository.orm;

import com.gridstudentpractice.chatservice.mapper.UserMapper;
import com.gridstudentpractice.chatservice.model.User;
import com.gridstudentpractice.chatservice.model.UserEntity;
import com.gridstudentpractice.chatservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository
public class ORMUserRepositoryImpl implements UserRepository {

    @Lazy
    @Autowired
    private ORMUserRepository ormUserRepository;

    @Autowired
    private UserMapper mapper;

    @Override
    public void createUser(User user) {
        ormUserRepository.save(mapper.toEntity(user));
    }

    @Override
    public User getUserByLogin(String userLogin) {
        return mapper.toDTO(ormUserRepository.findByLogin(userLogin));
    }

    @Override
    public void updateUser(User user) {
        ormUserRepository.save(ormUserRepository.findById(user.getId())
                .map(userEntity -> {
                    userEntity.setLogin(user.getLogin());
                    userEntity.setPassword(user.getPassword());
                    return userEntity;
                }).get());
    }

    @Override
    public void deleteUserById(int id) {
        ormUserRepository.deleteById(id);
    }
}
