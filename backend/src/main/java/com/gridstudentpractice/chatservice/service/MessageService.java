package com.gridstudentpractice.chatservice.service;

import com.gridstudentpractice.chatservice.model.MessageDto;

import java.util.List;

public interface MessageService {

    void sendMessage(MessageDto messageDto);
    List<MessageDto> getMessageDtos();
    void updateMessageBody(MessageDto messageDto);
    void deleteMessageById(int id);

}
