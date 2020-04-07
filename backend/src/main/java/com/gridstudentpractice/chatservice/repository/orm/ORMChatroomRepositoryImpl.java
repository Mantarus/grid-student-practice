package com.gridstudentpractice.chatservice.repository.orm;

import com.gridstudentpractice.chatservice.mapper.ChatroomMapper;
import com.gridstudentpractice.chatservice.model.Chatroom;
import com.gridstudentpractice.chatservice.model.ChatroomEntity;
import com.gridstudentpractice.chatservice.model.UserEntity;
import com.gridstudentpractice.chatservice.repository.ChatroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ORMChatroomRepositoryImpl implements ChatroomRepository {

    @Lazy
    @Autowired
    private ORMChatroomRepository ormChatroomRepository;

    @Lazy
    @Autowired
    private ORMUserRepository ormUserRepository;

    @Autowired
    private ChatroomMapper mapper;

    @Override
    public void createChatroom(Chatroom chatroom) {
        ormChatroomRepository.save(mapper.toEntity(chatroom));
    }

    @Override
    public Chatroom getChatroomById(int chatroomId) {
        return mapper.toDTO(ormChatroomRepository.getOne(chatroomId));
    }

    @Override
    public List<Chatroom> getChatroomsByName(String chatroomName) {
        List<Chatroom> chatrooms = new ArrayList<>();
        List<ChatroomEntity> chatroomEntities = ormChatroomRepository.findAllByName(chatroomName);

        for (ChatroomEntity chatroomEntity: chatroomEntities ) {
            chatrooms.add(mapper.toDTO(chatroomEntity));
        }

        return chatrooms;
    }

    @Override
    public void addUserToChatroom(int uId, int cId) {
        ChatroomEntity chatroomEntity = ormChatroomRepository.findChatroomEntityById(cId);
        UserEntity userEntity = ormUserRepository.findUserEntityById(uId);

        if (!chatroomEntity.getUserEntities().contains(userEntity))
            chatroomEntity.getUserEntities().add(userEntity);
    }

    @Override
    public void updateChatroom(Chatroom chatroom) {
        ChatroomEntity chatroomEntity = ormChatroomRepository.findChatroomEntityById(chatroom.getId());
        chatroomEntity.setName(chatroom.getName());
        chatroomEntity.setDescription(chatroom.getDescription());
        ormChatroomRepository.save(chatroomEntity);
    }

    @Override
    public void deleteChatroomById(int id) {
        ormChatroomRepository.deleteById(id);
    }
}
