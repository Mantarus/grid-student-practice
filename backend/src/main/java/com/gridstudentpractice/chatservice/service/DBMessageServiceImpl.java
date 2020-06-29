package com.gridstudentpractice.chatservice.service;

import com.gridstudentpractice.chatservice.model.MessageDto;
import com.gridstudentpractice.chatservice.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
 public class DBMessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void sendMessage(MessageDto messageDto) {
        messageRepository.createMessage(messageDto);
    }

    @Override
    public List<MessageDto> getMessageDtos() {
        return messageRepository.getMessages();
    }

    @Override
    public void updateMessageBody(MessageDto messageDto) {
        messageRepository.updateMessageBody(messageDto);
    }

    @Override
    public void deleteMessageById(int id) {
        messageRepository.deleteMessageById(id);
    }
}

