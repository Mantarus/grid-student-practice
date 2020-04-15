package com.gridstudentpractice.chatservice.repository.orm;

import com.gridstudentpractice.chatservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ORMUserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);
}
