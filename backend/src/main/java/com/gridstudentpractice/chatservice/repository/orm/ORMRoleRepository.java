package com.gridstudentpractice.chatservice.repository.orm;

import com.gridstudentpractice.chatservice.model.Role;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Profile("orm")
@Repository
public interface ORMRoleRepository extends JpaRepository<Role, Integer> {

    @EntityGraph(value = "role-entity-graph")
    @Query("SELECT r FROM Role r LEFT JOIN FETCH r.userEntities u WHERE u.login = ?1")
    List<Role> getUserRoles(String login);

}
