package com.gridstudentpractice.chatservice.controller;

import com.gridstudentpractice.chatservice.model.Chatroom;
import com.gridstudentpractice.chatservice.service.ChatroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/restChat/chatroom")
public class ChatroomRestController {

    @Autowired
    private ChatroomService chatroomService;

    @PostMapping("/post")
    public void addChatroom(@Valid @RequestBody Chatroom chatroom) {
        chatroomService.createChatroom(chatroom);
    }

}
