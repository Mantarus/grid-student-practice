package com.gridstudentpractice.chatservice.repository;

import com.gridstudentpractice.chatservice.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ORMUserRepository extends JpaRepository<UserEntity, Integer> {

}
