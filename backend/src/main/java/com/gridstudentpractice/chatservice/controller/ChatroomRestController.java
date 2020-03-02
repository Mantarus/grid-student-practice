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

    @PostMapping("/add-chatroom")
    public void addChatroom(@Valid @RequestBody Chatroom chatroom) {
        chatroomService.createChatroom(chatroom);
    }

    @PostMapping("/add-user")
    public void addUserToChatroom(@Valid @RequestBody User user, @Valid @RequestBody Chatroom chatroom) {
        chatroomService.addUserToChatroom(user, chatroom);
    }

    @GetMapping("/{id}")
    public Chatroom getChatroomById(@PathVariable int id) {
        return chatroomService.getChatroomById(id);
    }

    @GetMapping("?name={name}")
    public List<Chatroom> getChatroomsNyName(@PathVariable String name) {
        return chatroomService.getChatroomsByName(name);
    }

    @PutMapping("/edit-chatroom/{id}")
    public void editChatroom(@Valid @RequestBody Chatroom chatroom, @PathVariable int id) {
        chatroomService.editChatroom(chatroom, id);
    }

    @DeleteMapping("/delete-chatroom/{id}")
    public void deleteChatroom(@PathVariable int id) {
        chatroomService.deleteChatroom(id);
    }
}
