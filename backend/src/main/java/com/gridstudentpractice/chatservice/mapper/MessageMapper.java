package com.gridstudentpractice.chatservice.mapper;

import com.gridstudentpractice.chatservice.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper
public interface MessageMapper {

    @Mappings({
            @Mapping(target = "id", source = "messageEntity.id"),
            @Mapping(target = "sender", source = "messageEntity.sender", qualifiedByName = "senderDTO"),
            @Mapping(target = "body", source = "messageEntity.body"),
            @Mapping(target = "timestamp", source = "messageEntity.timestamp"),
            @Mapping(target = "chatroom", source = "messageEntity.chatroom", qualifiedByName = "chatroomDTO")
    })
    Message toDTO(MessageEntity messageEntity);

    @Named("senderDTO")
    default String entityLoginToString(UserEntity userEntity) {
        if (userEntity != null) {
            return userEntity.getLogin();
        } else return null;
    }

    @Named("chatroomDTO")
    default String entityChatroomToString(ChatroomEntity chatroomEntity) {
        if (chatroomEntity != null) {
            return chatroomEntity.getName();
        } else return null;
    }

    @Mappings({
            @Mapping(target = "id", source = "message.id"),
            @Mapping(target = "sender", source = "message.sender", qualifiedByName = "sender"),
            @Mapping(target = "body", source = "message.body"),
            @Mapping(target = "timestamp", source = "message.timestamp"),
            @Mapping(target = "chatroom", source = "message.chatroom", qualifiedByName = "chatroom")
    })
    MessageEntity toEntity(Message message);
    MessageMapperImpl
    @Named("sender")
    default UserEntity loginStringToEntity(String login) {
        if (login != null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(Integer.parseInt(login));
            return userEntity;
        } else return null;
    }

    @Named("chatroom")
    default ChatroomEntity chatroomStringToEntity(String chatroom) {
        if (chatroom != null) {
            ChatroomEntity chatroomEntity = new ChatroomEntity();
            chatroomEntity.setId(Integer.parseInt(chatroom));
            return chatroomEntity;
        } else return null;
    }

}
