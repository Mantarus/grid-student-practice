package com.gridstudentpractice.chatservice.repository.orm;

import com.gridstudentpractice.chatservice.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ORMUserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByLogin(String login);

}
