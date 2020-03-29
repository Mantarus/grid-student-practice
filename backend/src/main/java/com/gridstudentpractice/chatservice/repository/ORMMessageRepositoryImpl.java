package com.gridstudentpractice.chatservice.repository;

import com.gridstudentpractice.chatservice.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ORMMessageRepositoryImpl implements MessageRepository {

    @Lazy
    @Autowired
    ORMMessageRepository ormMessageRepository;

    @Override
    public void createMessage(Message message) {

    }

    @Override
    public List<Message> getMessages() {
        return null;
    }

    @Override
    public void updateMessage(Message message) {

    }

    @Override
    public void deleteMessageById(int id) {

    }
}
