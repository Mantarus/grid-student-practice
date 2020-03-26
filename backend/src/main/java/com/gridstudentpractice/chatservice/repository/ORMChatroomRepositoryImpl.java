package com.gridstudentpractice.chatservice.repository;

import com.gridstudentpractice.chatservice.model.Chatroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ORMChatroomRepositoryImpl implements ChatroomRepository {

    @Autowired
    ORMChatroomRepository ormChatroomRepository;

    @Override
    public void createChatroom(Chatroom chatroom) {

    }

    @Override
    public Chatroom getChatroomById(int chatroomId) {
        return null;
    }

    @Override
    public List<Chatroom> getChatroomsByName(String chatroomName) {
        return null;
    }

    @Override
    public void addUserToChatroom(int uId, int cId) {

    }

    @Override
    public void updateChatroom(Chatroom chatroom) {

    }

    @Override
    public void deleteChatroomById(int id) {

    }
}
