package com.gridstudentpractice.chatservice.mapper;

import com.gridstudentpractice.chatservice.model.User;
import com.gridstudentpractice.chatservice.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface UserMapper {

    @Mappings({
            @Mapping(target = "id", source = "userEntity.id"),
            @Mapping(target = "login", source = "userEntity.login"),
            @Mapping(target = "password", source = "userEntity.password")
    })
    User toDTO(UserEntity userEntity);

    @Mappings({
            @Mapping(target = "id", source = "user.id"),
            @Mapping(target = "login", source = "user.login"),
            @Mapping(target = "password", source = "user.password"),
            @Mapping(target = "chatroomEntities", ignore = true),
            @Mapping(target = "messageEntities", ignore = true)
    })
    UserEntity toEntity(User user);

}
