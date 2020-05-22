package com.gridstudentpractice.chatservice.service;

import com.gridstudentpractice.chatservice.model.MessageDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryMessageServiceImpl implements MessageService {

    private List<MessageDto> messageDtos = new ArrayList<>();

    @Override
    public void sendMessage(MessageDto messageDto) {
        messageDtos.add(messageDto);
    }

    @Override
    public List<MessageDto> getMessageDtos() {
        return new ArrayList<>(messageDtos);
    }

    @Override
    public void updateMessage(MessageDto messageDto) {

    }

    @Override
    public void deleteMessageById(int id) {

    }

}
