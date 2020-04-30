package com.gridstudentpractice.chatservice.service;

import com.gridstudentpractice.chatservice.model.ChatroomDto;

import java.util.List;

public interface ChatroomService {

    void createChatroom(ChatroomDto chatroomDto);
    ChatroomDto getChatroomById(int chatroomId);
    List<ChatroomDto> getChatroomsByName(String chatroomName);
    void addUserToChatroom(int uId, int cId);
    void updateChatroom(ChatroomDto chatroomDto);
    void deleteChatroomById(int id);

}
