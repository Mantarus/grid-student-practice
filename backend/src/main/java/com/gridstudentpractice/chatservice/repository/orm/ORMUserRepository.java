package com.gridstudentpractice.chatservice.repository.orm;

import com.gridstudentpractice.chatservice.model.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Profile("orm")
@Repository
public interface ORMUserRepository extends JpaRepository<User, Integer> {

    @EntityGraph(value = "user-entity-graph")
    @Query("SELECT u FROM User u WHERE u.login = ?1")
    User findByLogin(String login);

}
