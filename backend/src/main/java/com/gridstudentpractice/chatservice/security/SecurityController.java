package com.gridstudentpractice.chatservice.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SecurityController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/user")
    public String user() {
        return "user/index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

}
