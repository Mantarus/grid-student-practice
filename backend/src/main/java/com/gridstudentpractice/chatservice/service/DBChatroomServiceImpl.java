package com.gridstudentpractice.chatservice.service;

import com.gridstudentpractice.chatservice.model.Chatroom;
import com.gridstudentpractice.chatservice.model.User;
import com.gridstudentpractice.chatservice.repository.ChatroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBChatroomServiceImpl implements ChatroomService {

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
        chatroomRepository.addUserToChatroom(user, chatroom);
    }

    @Override
    public void editChatroom(Chatroom chatroom, int id) {
        chatroomRepository.updateChatroom(chatroom, id);
    }

    @Override
    public void deleteChatroom(int id) {
        chatroomRepository.deleteChatroom(id);
    }
}
