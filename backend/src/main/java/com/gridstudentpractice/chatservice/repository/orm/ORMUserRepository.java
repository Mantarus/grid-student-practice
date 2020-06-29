package com.gridstudentpractice.chatservice.repository.orm;

import com.gridstudentpractice.chatservice.model.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Profile("orm")
@Repository
public interface ORMUserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);

}
