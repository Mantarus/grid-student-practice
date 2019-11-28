package com.gridstudentpractice.chatservice.messages;

import java.util.List;

public interface MessageServiceInterface {
    void sendMessage(Message message);
    List<Message> getMessages();
}
