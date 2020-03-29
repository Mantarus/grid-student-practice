package com.gridstudentpractice.chatservice.repository;

import com.gridstudentpractice.chatservice.model.ChatroomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ORMChatroomRepository extends JpaRepository<ChatroomEntity, Integer> {

}
