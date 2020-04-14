package com.gridstudentpractice.chatservice.controller;

import com.gridstudentpractice.chatservice.model.MessageDto;
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
    public List<MessageDto> getAllMessages() {
        return messageService.getMessageDtos();
    }

    @PostMapping
    public void sendMessage(@Valid @RequestBody MessageDto messageDto) {
        messageService.sendMessage(messageDto);
    }

    @PutMapping
    public void editMessage(@RequestBody MessageDto messageDto) {
        messageService.updateMessage(messageDto);
    }

    @DeleteMapping("/{id}")
    public void deleteMessageById(@PathVariable int id) {
        messageService.deleteMessageById(id);
    }
}
