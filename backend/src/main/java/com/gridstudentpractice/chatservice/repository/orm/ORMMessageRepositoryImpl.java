package com.gridstudentpractice.chatservice.repository.orm;

import com.gridstudentpractice.chatservice.mapper.MessageMapper;
import com.gridstudentpractice.chatservice.model.MessageDto;
import com.gridstudentpractice.chatservice.model.Message;

import com.gridstudentpractice.chatservice.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("orm")
@Repository
public class ORMMessageRepositoryImpl implements MessageRepository {

    @Lazy
    @Autowired
    private ORMMessageRepository ormMessageRepository;

    @Autowired
    private MessageMapper mapper;

    @Override
    public void createMessage(MessageDto messageDto) {
        ormMessageRepository.save(mapper.toEntity(messageDto));
    }

    @Override
    public List<MessageDto> getMessages() {
        List<Message> messageEntities = ormMessageRepository.findMessages();
        return messageEntities.stream()
                .map(messageEntity -> mapper.toDTO(messageEntity))
                .collect(Collectors.toList());
    }

    @Override
    public void updateMessage(MessageDto messageDto) {
        ormMessageRepository.save(mapper.toEntity(messageDto));
    }

    @Override
    public void deleteMessageById(int id) {
        ormMessageRepository.deleteById(id);
    }
}
