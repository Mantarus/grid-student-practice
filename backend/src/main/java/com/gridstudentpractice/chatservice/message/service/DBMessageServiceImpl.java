package com.gridstudentpractice.chatservice.message.service;

import com.gridstudentpractice.chatservice.Application;
import com.gridstudentpractice.chatservice.message.model.Message;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
 public class DBMessageServiceImpl implements MessageService {

    @Override
    public Message sendMessage(Message message) throws SQLException {

        String mSender = message.getMSender();
        String mBody = message.getMBody();
        String mTime = message.getMTimestamp().toString();
        String insertTableSQL = "INSERT INTO messages (sender, body, time1) VALUES" + "('"  + mSender + "\', \'" + mBody + "\',\'" + mTime + "\')" ;

        System.out.println(insertTableSQL);

        Statement statement = null;
        statement = Application.getConnection().createStatement();
        statement.executeUpdate(insertTableSQL);
        System.out.println("SQL injection complited");

        if (statement != null)
            statement.close();

        return null;
    }

    @Override
    public List<Message> getMessages() throws SQLException {

        String selectTableSQL = "SELECT sender, body, time1 from messages";

        Statement statement = null;
        statement = Application.getConnection().createStatement();

        ResultSet rs = statement.executeQuery(selectTableSQL);

        List<Message> messages = new ArrayList<>();

        while (rs.next()) {

            String sender = rs.getString("sender");
            String body = rs.getString("body");
            String time1 = rs.getString("time1");

            Message message = new Message();
            message.setMSender(sender);
            message.setMBody(body);
            message.setMTimestamp(LocalDateTime.parse(time1));

            messages.add(message);

            System.out.println("sender : " + sender);
            System.out.println("body : " + body);
            System.out.println("time1 : " + time1);
        }
        return messages;
    }
}

