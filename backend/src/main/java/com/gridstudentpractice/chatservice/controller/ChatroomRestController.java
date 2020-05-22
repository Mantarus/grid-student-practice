package com.gridstudentpractice.chatservice.controller;

import com.gridstudentpractice.chatservice.model.ChatroomDto;
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
    public void addChatroom(@Valid @RequestBody ChatroomDto chatroomDto) {
        chatroomService.createChatroom(chatroomDto);
    }

    @PostMapping("/add-user/{userId}/{chatroomId}")
    public void addUserToChatroom(@PathVariable("userId") int userId, @PathVariable("chatroomId") int chatroomId) {
        chatroomService.addUserToChatroom(userId, chatroomId);
    }

    @GetMapping("/id/{id}")
    public ChatroomDto getChatroomById(@PathVariable int id) {
        return chatroomService.getChatroomById(id);
    }

    @GetMapping("/name/{name}")
    public List<ChatroomDto> getChatroomsByName(@PathVariable("name") String name) {
        return chatroomService.getChatroomsByName(name);
    }

    @PutMapping
    public void updateChatroom(@Valid @RequestBody ChatroomDto chatroomDto) {
        chatroomService.updateChatroom(chatroomDto);
    }

    @DeleteMapping("/{id}")
    public void deleteChatroomById(@PathVariable int id) {
        chatroomService.deleteChatroomById(id);
    }
}
