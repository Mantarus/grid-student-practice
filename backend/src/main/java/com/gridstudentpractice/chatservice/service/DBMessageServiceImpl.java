package com.gridstudentpractice.chatservice.service;

import com.gridstudentpractice.chatservice.model.Message;
import com.gridstudentpractice.chatservice.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
 public class DBMessageServiceImpl implements MessageService {

    @Qualifier("JDBCMessageRepositoryImpl")
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void sendMessage(Message message) {
        messageRepository.createMessage(message);
    }

    @Override
    public List<Message> getMessages() {
        return messageRepository.getMessages();
    }

    @Override
    public void updateMessage(Message message) {
        messageRepository.updateMessage(message);
    }

    @Override
    public void deleteMessageById(int id) {
        messageRepository.deleteMessageById(id);
    }
}

