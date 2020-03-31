package com.gridstudentpractice.chatservice.mapper;

import com.gridstudentpractice.chatservice.model.Chatroom;
import com.gridstudentpractice.chatservice.model.ChatroomEntity;
import com.gridstudentpractice.chatservice.model.User;
import com.gridstudentpractice.chatservice.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface ChatroomMapper {

    @Mappings({
            @Mapping(target = "id", source = "chatroomEntity.id"),
            @Mapping(target = "name", source = "chatroomEntity.name"),
            @Mapping(target = "description", source = "chatroomEntity.description")
    })
    Chatroom toDTO(ChatroomEntity chatroomEntity);

    @Mappings({
            @Mapping(target = "id", source = "chatroom.id"),
            @Mapping(target = "name", source = "chatroom.name"),
            @Mapping(target = "description", source = "chatroom.description"),
            @Mapping(target = "userEntities", ignore = true),
            @Mapping(target = "messageEntities", ignore = true)
    })
    ChatroomEntity toEntity(Chatroom chatroom);
}
