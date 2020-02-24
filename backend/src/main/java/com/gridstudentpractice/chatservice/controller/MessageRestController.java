package com.gridstudentpractice.chatservice.controller;

import com.gridstudentpractice.chatservice.model.Message;
import com.gridstudentpractice.chatservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/restChat/message")
public class MessageRestController {

    @Autowired
    @Qualifier("DBMessageServiceImpl")
    private MessageService messageService;

    @GetMapping("/get")
    public List<Message> getAllMessages() {
        return messageService.getMessages();
    }

    @PostMapping("/post")
    public void sendMessage(@Valid @RequestBody Message message) {
        messageService.sendMessage(message);
    }

}
