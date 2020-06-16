package com.gridstudentpractice.chatservice.controller;

import com.gridstudentpractice.chatservice.model.UserDto;
import com.gridstudentpractice.chatservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
    @Secured("ROLE_ADMIN")
    public void addUser(@Valid @RequestBody UserDto userDto) {
        userService.addUser(userDto);
    }

    @PostMapping("/add-role/{roleId}/{userId}")
    @Secured("ROLE_ADMIN")
    public void addRoleToUser(@PathVariable("roleId") int roleId, @PathVariable("userId") int userId) {
        userService.addRoleToUser(roleId, userId);
    }

    @PutMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void updateUserLoginAndPassword(@Valid @RequestBody UserDto userDto) {
        userService.updateUserLoginAndPassword(userDto);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public void deleteUserById(@PathVariable int id) {
        userService.deleteUserById(id);
    }
}
