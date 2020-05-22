package com.gridstudentpractice.chatservice.controller;

import com.gridstudentpractice.chatservice.model.UserDto;
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
    public UserDto getUserByLogin(@PathVariable String userLogin) {
        return userService.getUserByLogin(userLogin);
    }

    @PostMapping
    public void addUser(@Valid @RequestBody UserDto userDto) {
        userService.addUser(userDto);
    }

    @PutMapping
    public void updateUser(@Valid @RequestBody UserDto userDto) {
        userService.updateUser(userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable int id) {
        userService.deleteUserById(id);
    }
}
