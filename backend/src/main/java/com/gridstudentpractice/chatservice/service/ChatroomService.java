package com.gridstudentpractice.chatservice.service;

import com.gridstudentpractice.chatservice.model.Chatroom;
import com.gridstudentpractice.chatservice.model.User;

import java.util.List;

public interface ChatroomService {

    void createChatroom(Chatroom chatroom);
    Chatroom getChatroomById(int chatroomId);
    List<Chatroom> getChatroomsByName(String chatroomName);
    void addUserToChatroom(int uId, int cId);
    void updateChatroom(Chatroom chatroom);
    void deleteChatroomById(int id);

}
