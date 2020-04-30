package com.gridstudentpractice.chatservice.service;

import com.gridstudentpractice.chatservice.model.ChatroomDto;
import com.gridstudentpractice.chatservice.repository.ChatroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBChatroomServiceImpl implements ChatroomService {

    @Autowired
    private ChatroomRepository chatroomRepository;

    @Override
    public void createChatroom(ChatroomDto chatroomDto) {
        chatroomRepository.createChatroom(chatroomDto);
    }

    @Override
    public ChatroomDto getChatroomById(int chatroomId) {
        return chatroomRepository.getChatroomById(chatroomId);
    }

    @Override
    public List<ChatroomDto> getChatroomsByName(String chatroomName) {
        return chatroomRepository.getChatroomsByName(chatroomName);
    }

    @Override
    public void addUserToChatroom(int userId, int chatroomId) {
        chatroomRepository.addUserToChatroom(userId, chatroomId);
    }

    @Override
    public void updateChatroom(ChatroomDto chatroomDto) {
        chatroomRepository.updateChatroom(chatroomDto);
    }

    @Override
    public void deleteChatroomById(int id) {
        chatroomRepository.deleteChatroomById(id);
    }
}
