package com.gridstudentpractice.chatservice.repository;

import com.gridstudentpractice.chatservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ORMUserRepository extends JpaRepository<User, Long> {

}
