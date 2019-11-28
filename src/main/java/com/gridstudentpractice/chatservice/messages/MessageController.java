package com.gridstudentpractice.chatservice.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class MessageController {

    @Autowired
    private InMemoryMessageService messageService;

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
//        binder.registerCustomEditor(LocalDateTime.class, new CustomDateEditor(
//                dateFormat, true));
//    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAllMessages(Model model) {
        model.addAttribute("message", new Message());
        return "page";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String sendMessage(@Valid @ModelAttribute("message") Message message, Model model) {
        messageService.sendMessage(message);
        model.addAttribute("messages", messageService.getMessages());
        return "page";
    }

}
