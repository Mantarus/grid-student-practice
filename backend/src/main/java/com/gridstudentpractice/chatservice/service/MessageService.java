package com.gridstudentpractice.chatservice.service;

import com.gridstudentpractice.chatservice.model.Message;

import java.util.List;

public interface MessageService {

    void sendMessage(Message message);
    List<Message> getMessages();
    void editMessage(Message message, int id);
    void deleteMessage(int id);

}
