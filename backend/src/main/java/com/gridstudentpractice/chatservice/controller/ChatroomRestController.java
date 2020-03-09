package com.gridstudentpractice.chatservice.controller;

import com.gridstudentpractice.chatservice.model.Chatroom;
import com.gridstudentpractice.chatservice.model.User;
import com.gridstudentpractice.chatservice.service.ChatroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/chatrooms")
public class ChatroomRestController {

    @Autowired
    private ChatroomService chatroomService;

    @PostMapping
    public void addChatroom(@Valid @RequestBody Chatroom chatroom) {
        chatroomService.createChatroom(chatroom);
    }

    @PostMapping("/add-user")
    public void addUserToChatroom(@Valid @RequestBody User uId, @Valid @RequestBody Chatroom cId) {
        chatroomService.addUserToChatroom(uId, cId);
    }

    @GetMapping("/{id}")
    public Chatroom getChatroomById(@PathVariable int id) {
        return chatroomService.getChatroomById(id);
    }

    @GetMapping("?name={name}")
    public List<Chatroom> getChatroomsNyName(@PathVariable String name) {
        return chatroomService.getChatroomsByName(name);
    }

    @PutMapping
    public void updateChatroom(@Valid @RequestBody Chatroom chatroom) {
        chatroomService.updateChatroom(chatroom);
    }

    @DeleteMapping("/{id}")
    public void deleteChatroomById(@PathVariable int id) {
        chatroomService.deleteChatroomById(id);
    }
}
