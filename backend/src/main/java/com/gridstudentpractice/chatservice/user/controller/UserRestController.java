package com.gridstudentpractice.chatservice.user.controller;

import com.gridstudentpractice.chatservice.user.model.User;
import com.gridstudentpractice.chatservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/restChat/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping
    public boolean checkUser(@Valid @RequestBody User user) {
        return userService.checkUser(user);
    }

    @PostMapping
    public void addUser(@Valid @RequestBody User user) {
        userService.addUser(user);
    }
}
