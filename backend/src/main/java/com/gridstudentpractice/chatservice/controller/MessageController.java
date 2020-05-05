package com.gridstudentpractice.chatservice.controller;

import com.gridstudentpractice.chatservice.model.MessageDto;
import com.gridstudentpractice.chatservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/chat")
public class MessageController {

    @Autowired
    @Qualifier("DBMessageServiceImpl")
    private MessageService messageService;

    @RequestMapping(method = RequestMethod.GET)
    public String displayMessages(Model model) {
        model.addAttribute("message", MessageDto.builder().build());
        return "page";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String sendMessage(@Valid @ModelAttribute("message") MessageDto messageDto, Model model) {
        messageService.sendMessage(messageDto);
        model.addAttribute("messages", messageService.getMessageDtos());
        return "page";
    }

}
