package com.gridstudentpractice.chatservice.security;

import com.gridstudentpractice.chatservice.model.UserDto;
import com.gridstudentpractice.chatservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserDto userRegistrationDto() {
        return UserDto.builder().build();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto,
                                      BindingResult result){

        UserDto existing = userService.getUserByLogin(userDto.getLogin());
        if (existing != null){
            result.rejectValue("login", null, "There is already an account registered with that login");
        }

        if (result.hasErrors()){
            return "registration";
        }

        userService.addUser(userDto);
        return "redirect:/registration?success";
    }

}