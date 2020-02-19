package com.gridstudentpractice.chatservice.service;

import com.gridstudentpractice.chatservice.model.Message;
import com.gridstudentpractice.chatservice.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
 public class DBMessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void sendMessage(Message message) {
        messageRepository.createMessage(message);
    }

    @Override
    public List<Message> getMessages() {
        return messageRepository.readMessages();
    }
}

