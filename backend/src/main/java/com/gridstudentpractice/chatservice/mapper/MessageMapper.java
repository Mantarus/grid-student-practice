package com.gridstudentpractice.chatservice.mapper;

import com.gridstudentpractice.chatservice.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface MessageMapper {

    @Mappings({
            @Mapping(target = "id", source = "message.id"),
            @Mapping(target = "sender", source = "message.sender.login"),
            @Mapping(target = "body", source = "message.body"),
            @Mapping(target = "timestamp", source = "message.timestamp"),
            @Mapping(target = "chatroom", source = "message.chatroom.name")
    })
    MessageDto toDTO(Message message);

    @Mappings({
            @Mapping(target = "id", source = "messageDto.id"),
            @Mapping(target = "sender.id", source = "messageDto.sender"),
            @Mapping(target = "body", source = "messageDto.body"),
            @Mapping(target = "timestamp", source = "messageDto.timestamp"),
            @Mapping(target = "chatroom.id", source = "messageDto.chatroom")
    })
    Message toEntity(MessageDto messageDto);
}
