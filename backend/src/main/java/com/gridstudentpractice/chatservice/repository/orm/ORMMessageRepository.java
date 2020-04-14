package com.gridstudentpractice.chatservice.repository.orm;

import com.gridstudentpractice.chatservice.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ORMMessageRepository extends JpaRepository<Message, Integer> {
}
