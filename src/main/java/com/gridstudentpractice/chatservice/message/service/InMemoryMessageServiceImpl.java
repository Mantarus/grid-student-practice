package com.gridstudentpractice.chatservice.message.service;


import com.gridstudentpractice.chatservice.message.model.Message;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Component
public class InMemoryMessageServiceImpl implements MessageService {

    private Map<Long, Message> messages = new HashMap<>();

    @Override
    public void sendMessage(Message message) {
        messages.put(message.getMID(), message);
    }

    @Override
    public List<Message> getMessages() {
        return new ArrayList<>(messages.values());
    }

}
