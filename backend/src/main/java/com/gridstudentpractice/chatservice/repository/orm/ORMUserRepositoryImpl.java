package com.gridstudentpractice.chatservice.repository.orm;

import com.gridstudentpractice.chatservice.mapper.UserMapper;
import com.gridstudentpractice.chatservice.model.User;
import com.gridstudentpractice.chatservice.model.UserEntity;
import com.gridstudentpractice.chatservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
        Optional<UserEntity> optionalUserEntity = ormUserRepository.findById(user.getId());
        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            userEntity.setPassword(user.getPassword());
            userEntity.setLogin(user.getLogin());
            ormUserRepository.save(userEntity);
        }
    }

    @Override
    public void deleteUserById(int id) {
        ormUserRepository.deleteById(id);
    }
}
