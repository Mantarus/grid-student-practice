package com.gridstudentpractice.chatservice.repository;

import com.gridstudentpractice.chatservice.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ORMMessageRepository extends JpaRepository<Message, Long> {
}
