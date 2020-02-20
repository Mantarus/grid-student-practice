package com.gridstudentpractice.chatservice.repository;

import com.gridstudentpractice.chatservice.model.Message;

import java.util.List;

public interface MessageRepository {

    boolean createMessage(Message message);
    List<Message> getMessages();

}
