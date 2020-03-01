package com.gridstudentpractice.chatservice.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @NotNull
    private int Id;
    @NotBlank
    private String login;
    @NotBlank
    private String password;

}
