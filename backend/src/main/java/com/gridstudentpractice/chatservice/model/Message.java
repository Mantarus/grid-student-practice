package com.gridstudentpractice.chatservice.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @NotNull
    private int id;
    @NotBlank
    private String sender;
    @NotBlank
    private  String chatroom;
    @NotBlank
    private String body;
    private LocalDateTime timestamp;

}
