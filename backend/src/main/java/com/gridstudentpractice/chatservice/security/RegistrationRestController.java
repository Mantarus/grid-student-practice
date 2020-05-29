package com.gridstudentpractice.chatservice.security;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/springSecurity")
public class RegistrationRestController {

    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome</h1>");
    }

    @GetMapping("/user")
    public String user() {
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }

}
