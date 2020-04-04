package com.gridstudentpractice.chatservice.repository.orm;

import com.gridstudentpractice.chatservice.mapper.MessageMapper;
import com.gridstudentpractice.chatservice.model.Message;
import com.gridstudentpractice.chatservice.model.MessageEntity;
import com.gridstudentpractice.chatservice.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class ORMMessageRepositoryImpl implements MessageRepository {

    @Lazy
    @Autowired
    private ORMMessageRepository ormMessageRepository;

    @Autowired
    private MessageMapper mapper;

    @Override
    public void createMessage(Message message) {
        ormMessageRepository.save(mapper.toEntity(message));
    }

    @Override
    public List<Message> getMessages() {
        List<MessageEntity> messageEntities = ormMessageRepository.findAll();
        return messageEntities.stream()
                .map(messageEntity -> mapper.toDTO(messageEntity))
                .collect(Collectors.toList());
    }

    @Override
    public void updateMessage(Message message) {
        ormMessageRepository.save(ormMessageRepository.findById(message.getId())
                .map(messageEntity -> {
                   messageEntity.setBody(message.getBody());
                   return messageEntity;
                }).get());
    }

    @Override
    public void deleteMessageById(int id) {
        ormMessageRepository.deleteById(id);
    }
}
