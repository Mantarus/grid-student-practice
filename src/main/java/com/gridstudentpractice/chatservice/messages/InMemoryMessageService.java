package com.gridstudentpractice.chatservice.messages;


import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class InMemoryMessageService implements MessageServiceInterface {

    private Map<Long,Message> messages = new HashMap<>();
    private LocalDateTime today = new Message().getMTimestamp();

    @Override
    public void sendMessage(Message message) {
        if (message.getMID()==0) message.setMID((long) messages.size() + 1);
        messages.put(message.getMID(), message);
    }

    @Override
    public List<Message> getMessages() {
        return new ArrayList<>(messages.values());
    }

}
