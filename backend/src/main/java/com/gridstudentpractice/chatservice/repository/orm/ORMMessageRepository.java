package com.gridstudentpractice.chatservice.repository.orm;

import com.gridstudentpractice.chatservice.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ORMMessageRepository extends JpaRepository<MessageEntity, Integer> {
}
