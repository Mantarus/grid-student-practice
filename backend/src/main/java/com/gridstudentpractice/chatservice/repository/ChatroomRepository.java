package com.gridstudentpractice.chatservice.repository;

import com.gridstudentpractice.chatservice.model.Chatroom;
import com.gridstudentpractice.chatservice.model.User;

import java.util.List;

public interface ChatroomRepository {

    void createChatroom(Chatroom chatroom);
    Chatroom getChatroomById(int chatroomId);
    List<Chatroom> getChatroomsByName(String chatroomName);
    void addUserToChatroom(User user, Chatroom chatroom);
    void updateChatroom(Chatroom chatroom, int id);
    void deleteChatroom(int id);

}
