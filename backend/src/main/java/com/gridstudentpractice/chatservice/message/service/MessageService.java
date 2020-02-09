package com.gridstudentpractice.chatservice.message.service;

import com.gridstudentpractice.chatservice.message.model.Message;

import java.sql.SQLException;
import java.util.List;

public interface MessageService {
    Message sendMessage(Message message) throws SQLException;
    List<Message> getMessages();
}
