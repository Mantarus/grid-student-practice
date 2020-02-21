package com.gridstudentpractice.chatservice.service;

import com.gridstudentpractice.chatservice.AppProperties;
import com.gridstudentpractice.chatservice.DbUtil;
import com.gridstudentpractice.chatservice.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
 public class DBMessageServiceImpl implements MessageService {

    final static String insertTableSql = "INSERT INTO messages (sender, body, time1) VALUES (\'?\', \'?\',\'?\')" ;
    final static String selectTableSql = "SELECT sender, body, time1 from messages";

    @Autowired
    private AppProperties appProperties;

    @Override
    public Message sendMessage(Message message) {

        String mSender = message.getSender();
        String mBody = message.getBody();
        String mTime = message.getTimestamp().toString();

        try (PreparedStatement preparedStatement = DbUtil.getConnection(appProperties).prepareStatement(insertTableSql)) {

            preparedStatement.setString(1, mSender);
            preparedStatement.setString(2, mBody);
            preparedStatement.setString(3, mTime);

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Message> getMessages() {

        try (Statement statement = DbUtil.getConnection(appProperties).createStatement()) {
            try (ResultSet rs = statement.executeQuery(selectTableSql)) {
                List<Message> messages = new ArrayList<>();

                while (rs.next()) {

                    String sender = rs.getString("sender");
                    String body = rs.getString("body");
                    String time1 = rs.getString("time1");

                    Message message = new Message();
                    message.setSender(sender);
                    message.setBody(body);
                    message.setTimestamp(LocalDateTime.parse(time1));

                    messages.add(message);

                    String out = String.format("%s %s : %s", time1, sender, body);
                    System.out.println(out);

                    statement.close();
                }

                return messages;

            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

