package com.gridstudentpractice.chatservice.repository;

import com.gridstudentpractice.chatservice.model.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ORMChatroomRepository extends JpaRepository<Chatroom, Long> {

}
