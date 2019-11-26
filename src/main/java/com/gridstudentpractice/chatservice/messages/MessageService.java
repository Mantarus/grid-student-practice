package com.gridstudentpractice.chatservice.messages;


import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MessageService {

    private static AtomicLong mCounter = new AtomicLong();
    private Date today = Calendar.getInstance().getTime();

    private List<Message> messageList = Arrays.asList(
            new Message(mCounter.incrementAndGet(), "Trofim", "Hey!", today  ),
            new Message(mCounter.incrementAndGet(), "Ivan", "Hey, bruh..", today),
            new Message(mCounter.incrementAndGet(), "Trofim", "Nah, not bruh...", today)
            );

    public static void sendMessage(Message message) {
        messageList.add(message);
    }

    public List<Message> getMessages() {
        return messageList;
    }

    //NOT WORKING YET
    public Message getMessage(int id) {
        return messageList.get(id);
    }
}
