package com.gridstudentpractice.chatservice.security;

import com.gridstudentpractice.chatservice.mapper.UserMapper;
import com.gridstudentpractice.chatservice.model.User;
import com.gridstudentpractice.chatservice.service.DbUserServiceImpl;
import com.gridstudentpractice.chatservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
