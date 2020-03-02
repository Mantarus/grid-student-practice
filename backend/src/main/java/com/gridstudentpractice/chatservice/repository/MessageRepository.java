package com.gridstudentpractice.chatservice.repository;

import com.gridstudentpractice.chatservice.model.Message;

import java.util.List;

public interface MessageRepository {

    void createMessage(Message message);
    List<Message> getMessages();
    void updateMessage(Message message, int id);
    void deleteMessage(int id);

}
