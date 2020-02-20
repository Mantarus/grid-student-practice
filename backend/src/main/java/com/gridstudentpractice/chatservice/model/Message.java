package com.gridstudentpractice.chatservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private int id;
    @NotBlank
    private String sender;
    @NotBlank
    private String body;
    private LocalDateTime timestamp;

}
