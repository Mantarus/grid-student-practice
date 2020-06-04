package com.gridstudentpractice.chatservice.model;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class RoleDto {

    @NotNull
    private int id;
    @NotBlank
    private String name;

}
