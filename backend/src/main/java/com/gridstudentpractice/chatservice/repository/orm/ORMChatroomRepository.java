package com.gridstudentpractice.chatservice.repository.orm;

import com.gridstudentpractice.chatservice.model.ChatroomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ORMChatroomRepository extends JpaRepository<ChatroomEntity, Integer> {

    List<ChatroomEntity> findAllByName(String name);

    ChatroomEntity findChatroomEntityById(Integer id);

}
