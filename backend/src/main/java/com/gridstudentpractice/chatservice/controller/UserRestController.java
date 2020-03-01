package com.gridstudentpractice.chatservice.controller;

import com.gridstudentpractice.chatservice.model.User;
import com.gridstudentpractice.chatservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/{user-login}")
    public User getUser(@PathVariable String userLogin) {
        return userService.getUser(userLogin);
    }

    @PostMapping("/add-user")
    public void addUser(@Valid @RequestBody User user) {
        userService.addUser(user);
    }
}
