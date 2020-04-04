package com.gridstudentpractice.chatservice.repository.orm;

import com.gridstudentpractice.chatservice.mapper.MessageMapper;
import com.gridstudentpractice.chatservice.model.Message;
import com.gridstudentpractice.chatservice.model.MessageEntity;

import com.gridstudentpractice.chatservice.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
        Optional<MessageEntity> optionalUserEntity = ormMessageRepository.findById(message.getId());
        if (optionalUserEntity.isPresent()) {
            MessageEntity messageEntity = optionalUserEntity.get();
            messageEntity.setBody(message.getBody());
            ormMessageRepository.save(messageEntity);
        }
    }

    @Override
    public void deleteMessageById(int id) {
        ormMessageRepository.deleteById(id);
    }
}
