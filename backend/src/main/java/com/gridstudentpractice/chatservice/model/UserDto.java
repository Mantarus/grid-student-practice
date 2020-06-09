package com.gridstudentpractice.chatservice.model;

import com.gridstudentpractice.chatservice.constraint.FieldMatch;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "The password must match"
)
public class UserDto {

    @NotNull
    private int id;
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;

}
