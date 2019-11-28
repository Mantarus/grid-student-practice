package com.gridstudentpractice.chatservice.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class MessageRestController {

    @Autowired
    private InMemoryMessageService messageService;

    @RequestMapping("/restMessages")
    public List<Message> getAllMessages() {
        return new InMemoryMessageService().getMessages();
    }

}
