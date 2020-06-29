package com.gridstudentpractice.chatservice.repository;

import com.gridstudentpractice.chatservice.model.MessageDto;

import java.util.List;

public interface MessageRepository {

    void createMessage(MessageDto messageDto);
    List<MessageDto> getMessages();
    void updateMessageBody(MessageDto messageDto);
    void deleteMessageById(int id);

}
