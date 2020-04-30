package com.gridstudentpractice.chatservice.repository.orm;

import com.gridstudentpractice.chatservice.model.Chatroom;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Profile("orm")
@Repository
public interface ORMChatroomRepository extends JpaRepository<Chatroom, Integer> {

    List<Chatroom> findAllByName(String name);
}
