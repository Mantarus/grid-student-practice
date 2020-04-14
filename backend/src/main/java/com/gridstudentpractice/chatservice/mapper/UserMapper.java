package com.gridstudentpractice.chatservice.mapper;

import com.gridstudentpractice.chatservice.model.User;
import com.gridstudentpractice.chatservice.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface UserMapper {

    @Mappings({
            @Mapping(target = "id", source = "user.id"),
            @Mapping(target = "login", source = "user.login"),
            @Mapping(target = "password", source = "user.password")
    })
    UserDto toDTO(User user);

    @Mappings({
            @Mapping(target = "id", source = "userDto.id"),
            @Mapping(target = "login", source = "userDto.login"),
            @Mapping(target = "password", source = "userDto.password"),
            @Mapping(target = "chatroomEntities", ignore = true),
            @Mapping(target = "messageEntities", ignore = true)
    })
    User toEntity(UserDto userDto);

}
