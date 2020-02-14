package com.gridstudentpractice.chatservice.message.service;

import com.gridstudentpractice.chatservice.message.model.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryMessageServiceImpl implements MessageService {

    private List<Message> messages = new ArrayList<>();

    @Override
    public Message sendMessage(Message message) {
        messages.add(message);
        return message;
    }

    @Override
    public List<Message> getMessages() {
        return new ArrayList<>(messages);
    }

}
