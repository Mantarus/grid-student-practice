package com.gridstudentpractice.chatservice.repository.orm;

import com.gridstudentpractice.chatservice.model.Message;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Profile("orm")
@Repository
public interface ORMMessageRepository extends JpaRepository<Message, Integer> {

    @EntityGraph(value = "message-entity-graph")
    @Query("SELECT m FROM Message m")
    List<Message> findMessages();

}
