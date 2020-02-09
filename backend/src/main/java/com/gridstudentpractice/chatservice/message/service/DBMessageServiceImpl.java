package com.gridstudentpractice.chatservice.message.service;

import com.gridstudentpractice.chatservice.Application;
import com.gridstudentpractice.chatservice.message.model.Message;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Service
 public class DBMessageServiceImpl implements MessageService {

    @Override
    public Message sendMessage(Message message) throws SQLException {
        String insertTableSQL = "INSERT INTO DBUSER"
                + "(sender, body, time) " + "VALUES"
                + "(Message.getMSender()),'mkyong','system', " + "";

        Statement statement = null;
        statement = Application.getConnection().createStatement();
        statement.executeUpdate(insertTableSQL);
        
        return null;
    }

    @Override
    public List<Message> getMessages() {
        return null;
    }
}

