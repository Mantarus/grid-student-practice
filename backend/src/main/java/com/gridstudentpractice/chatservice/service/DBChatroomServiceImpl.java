package com.gridstudentpractice.chatservice.service;

import com.gridstudentpractice.chatservice.model.Chatroom;
import com.gridstudentpractice.chatservice.model.User;
import com.gridstudentpractice.chatservice.repository.ChatroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBChatroomServiceImpl implements ChatroomService {

    @Qualifier("JDBCChatroomRepositoryImpl")
    @Autowired
    private ChatroomRepository chatroomRepository;

    @Override
    public void createChatroom(Chatroom chatroom) {
        chatroomRepository.createChatroom(chatroom);
    }

    @Override
    public Chatroom getChatroomById(int chatroomId) {
        return chatroomRepository.getChatroomById(chatroomId);
    }

    @Override
    public List<Chatroom> getChatroomsByName(String chatroomName) {
        return chatroomRepository.getChatroomsByName(chatroomName);
    }

    @Override
    public void addUserToChatroom(User user, Chatroom chatroom) {
        chatroomRepository.addUserToChatroom(user.getId(), chatroom.getId());
    }

    @Override
    public void updateChatroom(Chatroom chatroom) {
        chatroomRepository.updateChatroom(chatroom);
    }

    @Override
    public void deleteChatroomById(int id) {
        chatroomRepository.deleteChatroomById(id);
    }
}
