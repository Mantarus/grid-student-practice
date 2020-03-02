package com.gridstudentpractice.chatservice.controller;

import com.gridstudentpractice.chatservice.model.User;
import com.gridstudentpractice.chatservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userLogin}")
    public User getUser(@PathVariable String userLogin) {
        return userService.getUser(userLogin);
    }

    @PostMapping("/add-user")
    public void addUser(@Valid @RequestBody User user) {
        userService.addUser(user);
    }

    @PutMapping("/{id}/edit-user")
    public void editUser(@Valid @RequestBody User user, @PathVariable int id) {
        userService.editUser(user, id);
    }

    @DeleteMapping("/{id}/delete-user")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
