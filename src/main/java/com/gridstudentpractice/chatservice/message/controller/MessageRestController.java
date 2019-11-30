package com.gridstudentpractice.chatservice.message.controller;

import com.gridstudentpractice.chatservice.message.model.Message;
import com.gridstudentpractice.chatservice.message.service.InMemoryMessageServiceImpl;
import com.gridstudentpractice.chatservice.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class MessageRestController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/restMessages")
    public List<Message> getAllMessages() {
        return new InMemoryMessageServiceImpl().getMessages();
    }

}
