package com.gridstudentpractice.chatservice.service;

import com.gridstudentpractice.chatservice.model.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryMessageServiceImpl implements MessageService {

    private List<Message> messages = new ArrayList<>();

    @Override
    public void sendMessage(Message message) {
        messages.add(message);
    }

    @Override
    public List<Message> getMessages() {
        return new ArrayList<>(messages);
    }

    @Override
    public void editMessage(Message message, int id) {

    }

    @Override
    public void deleteMessage(int id) {

    }

}
