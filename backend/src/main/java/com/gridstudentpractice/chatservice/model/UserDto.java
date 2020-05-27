package com.gridstudentpractice.chatservice.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class UserDto {

    @NotNull
    private int id;
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    @NotBlank
    private String roles;

}
