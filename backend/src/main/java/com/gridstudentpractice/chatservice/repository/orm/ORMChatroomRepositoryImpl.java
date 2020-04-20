package com.gridstudentpractice.chatservice.repository.orm;

import com.gridstudentpractice.chatservice.exception.NoEntityException;
import com.gridstudentpractice.chatservice.mapper.ChatroomMapper;
import com.gridstudentpractice.chatservice.model.Chatroom;
import com.gridstudentpractice.chatservice.model.ChatroomDto;
import com.gridstudentpractice.chatservice.model.User;
import com.gridstudentpractice.chatservice.repository.ChatroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Profile("orm")
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
    public void createChatroom(ChatroomDto chatroomDto) {
        ormChatroomRepository.save(mapper.toEntity(chatroomDto));
    }

    @Override
    public ChatroomDto getChatroomById(int chatroomId) {
        return mapper.toDTO(ormChatroomRepository.findById(chatroomId).orElseThrow(()
                -> new NoEntityException("No chatroom with id" + chatroomId)));
    }

    @Override
    public List<ChatroomDto> getChatroomsByName(String chatroomName) {
        List<ChatroomDto> chatroomDtos = new ArrayList<>();
        List<Chatroom> chatroomEntities = ormChatroomRepository.findAllByName(chatroomName);

        for (Chatroom chatroom : chatroomEntities ) {
            chatroomDtos.add(mapper.toDTO(chatroom));
        }

        return chatroomDtos;
    }

    @Override
    public void addUserToChatroom(int userId, int chatroomId) {
        Chatroom chatroom = ormChatroomRepository.findById(chatroomId).orElseThrow(()
                -> new NoEntityException("No chatroom with id " + chatroomId));
        User user = ormUserRepository.findById(userId).orElseThrow(()
                -> new NoEntityException("No user with id " + userId));

        if (!chatroom.getUserEntities().contains(user)) {
            chatroom.getUserEntities().add(user);
            ormChatroomRepository.save(chatroom);
        }

    }

    @Override
    public void updateChatroom(ChatroomDto chatroomDto) {
        ormChatroomRepository.save(mapper.toEntity(chatroomDto));
    }

    @Override
    public void deleteChatroomById(int id) {
        ormChatroomRepository.deleteById(id);
    }
}
