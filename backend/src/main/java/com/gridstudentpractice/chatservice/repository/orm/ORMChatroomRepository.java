package com.gridstudentpractice.chatservice.repository.orm;

import com.gridstudentpractice.chatservice.model.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ORMChatroomRepository extends JpaRepository<Chatroom, Integer> {

    List<Chatroom> findAllByName(String name);
}
