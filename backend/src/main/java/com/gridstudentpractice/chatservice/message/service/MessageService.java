package com.gridstudentpractice.chatservice.message.service;

import com.gridstudentpractice.chatservice.message.model.Message;

import java.util.List;

public interface MessageService {
    Message sendMessage(Message message);
    List<Message> getMessages();
}
