package com.gridstudentpractice.chatservice.mapper;

import com.gridstudentpractice.chatservice.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface MessageMapper {

    @Mappings({
            @Mapping(target = "id", source = "messageEntity.id"),
            @Mapping(target = "sender", source = "messageEntity.sender.login"),
            @Mapping(target = "body", source = "messageEntity.body"),
            @Mapping(target = "timestamp", source = "messageEntity.timestamp"),
            @Mapping(target = "chatroom", source = "messageEntity.chatroom.name")
    })
    Message toDTO(MessageEntity messageEntity);

    @Mappings({
            @Mapping(target = "id", source = "message.id"),
            @Mapping(target = "sender.id", source = "message.sender"),
            @Mapping(target = "body", source = "message.body"),
            @Mapping(target = "timestamp", source = "message.timestamp"),
            @Mapping(target = "chatroom.id", source = "message.chatroom")
    })
    MessageEntity toEntity(Message message);
}
