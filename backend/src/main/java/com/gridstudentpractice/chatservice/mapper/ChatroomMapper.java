package com.gridstudentpractice.chatservice.mapper;

import com.gridstudentpractice.chatservice.model.Chatroom;
import com.gridstudentpractice.chatservice.model.ChatroomDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface ChatroomMapper {

    @Mappings({
            @Mapping(target = "id", source = "chatroom.id"),
            @Mapping(target = "name", source = "chatroom.name"),
            @Mapping(target = "description", source = "chatroom.description")
    })
    ChatroomDto toDTO(Chatroom chatroom);

    @Mappings({
            @Mapping(target = "id", source = "chatroomDto.id"),
            @Mapping(target = "name", source = "chatroomDto.name"),
            @Mapping(target = "description", source = "chatroomDto.description"),
            @Mapping(target = "userEntities", ignore = true),
            @Mapping(target = "messageEntities", ignore = true)
    })
    Chatroom toEntity(ChatroomDto chatroomDto);
}
