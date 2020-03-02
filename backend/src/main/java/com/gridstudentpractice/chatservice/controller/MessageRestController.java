package com.gridstudentpractice.chatservice.controller;

import com.gridstudentpractice.chatservice.model.Message;
import com.gridstudentpractice.chatservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/messages")
public class MessageRestController {

    @Autowired
    @Qualifier("DBMessageServiceImpl")
    private MessageService messageService;

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getMessages();
    }

    @PostMapping("/send-message")
    public void sendMessage(@Valid @RequestBody Message message) {
        messageService.sendMessage(message);
    }

    @PutMapping("/{id}/edit-message")
    public void editMessage(@RequestBody Message message, @PathVariable int id) {
        messageService.editMessage(message, id);
    }

    @DeleteMapping("/{id}/delete-message")
    public void deleteMessage(@PathVariable int id) {
        messageService.deleteMessage(id);
    }
}
