package com.gridstudentpractice.chatservice.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/messages")
    public List<Message> getAllMessages() {
        return new MessageService().getMessages();
    }

    //NOT WORKING YET
    @RequestMapping("/messages/{mId}")
    public Message getMessage(@PathVariable("mId") int id) {
        return new MessageService().getMessage(id);
    }

//    @RequestMapping(value = "/messages", method = RequestMethod.POST)
//    public void sendMessage(@RequestBody Message message) {
//        MessageService.sendMessage(message);
//    }

}
