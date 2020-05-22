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

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Profile("orm")
@Repository
public class ORMChatroomRepositoryImpl implements ChatroomRepository {

    @Lazy
    @Autowired
    private ORMChatroomRepository ormChatroomRepository;

    @Autowired
    private ChatroomMapper mapper;

    @Autowired
    private EntityManager entityManager;

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
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("chatroom-entity-graph");
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", entityGraph);
        Chatroom chatroom = entityManager.find(Chatroom.class, chatroomId, properties);
        User user = entityManager.find(User.class, userId, properties);

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
