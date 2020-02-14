package com.gridstudentpractice.chatservice.message.controller;

import com.gridstudentpractice.chatservice.message.model.Message;
import com.gridstudentpractice.chatservice.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/restChat")
public class MessageRestController {

    @Autowired
    @Qualifier("DBMessageServiceImpl")
    private MessageService messageService;

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getMessages();
    }

    @PostMapping
    public void sendMessage(@Valid @RequestBody Message message) {
        messageService.sendMessage(message);
    }

}
