package com.gridstudentpractice.chatservice.repository.orm;

import com.gridstudentpractice.chatservice.mapper.MessageMapper;
import com.gridstudentpractice.chatservice.model.Message;
import com.gridstudentpractice.chatservice.model.MessageEntity;
import com.gridstudentpractice.chatservice.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
        List<Message> messages = new ArrayList<>();
        List<MessageEntity> messageEntities = ormMessageRepository.findAll();
        for (MessageEntity messageEntity : messageEntities) {
            messages.add(mapper.toDTO(messageEntity));
        }
        return messages;
    }

    @Override
    public void updateMessage(Message message) {
        if (ormMessageRepository.findById(message.getId()).isPresent()) {
            MessageEntity messageEntity = ormMessageRepository.findById(message.getId()).get();
            messageEntity.setBody(message.getBody());
            ormMessageRepository.save(messageEntity);
        }
    }

    @Override
    public void deleteMessageById(int id) {
        ormMessageRepository.deleteById(id);
    }
}
