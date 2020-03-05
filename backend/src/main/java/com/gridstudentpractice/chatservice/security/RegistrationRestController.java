package com.gridstudentpractice.chatservice.security;

import com.gridstudentpractice.chatservice.model.User;
import com.gridstudentpractice.chatservice.service.DbUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/restChat/registration")
public class RegistrationRestController {

    @Autowired
    private DbUserServiceImpl dbUserService;

    @Autowired
    private SecurityService securityService;

    @PostMapping
    public String registration(@Valid @RequestBody User user) {

        if (dbUserService.getUser(user.getLogin()) == null) {
            return "This user already exists";
        }

        dbUserService.addUser(user);

        securityService.autoLogin(user.getLogin(), user.getPassword());

        return "User successfully registered";
    }
}
