package com.gridstudentpractice.chatservice.model;

import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class ChatroomDto {

    @NotNull
    private int id;
    @NotBlank
    private String name;
    private String description;

}
