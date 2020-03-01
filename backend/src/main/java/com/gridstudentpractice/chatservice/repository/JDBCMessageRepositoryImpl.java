package com.gridstudentpractice.chatservice.repository;

import com.gridstudentpractice.chatservice.DbUtil;
import com.gridstudentpractice.chatservice.exception.RepositoryException;
import com.gridstudentpractice.chatservice.model.Message;
import org.springframework.stereotype.Repository;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JDBCMessageRepositoryImpl implements MessageRepository {

    final static private String insertTableSql = "INSERT INTO messages (sender, body, chatroom) VALUES (?::integer, ?, ?::integer)";
    final static private String selectTableSql = "SELECT m.id AS id, " +
                                                "u.login AS sender, " +
                                                "m.body AS body, " +
                                                "m.time1 AS time1, " +
                                                "ch.name AS chatroom " +
            "FROM messages m " +
            "JOIN users u ON u.id=m.sender " +
            "JOIN chatrooms ch ON  ch.id=m.chatroom";

    @Override
    public void createMessage(Message message) {

        try (PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(insertTableSql)) {

            preparedStatement.setString(1, message.getSender());
            preparedStatement.setString(2, message.getBody());
            preparedStatement.setString(3,message.getChatroom());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException("Message creation error", e);
        }
    }

    @Override
    public List<Message> getMessages() {
        try (Statement statement = DbUtil.getConnection().createStatement()) {
            try (ResultSet rs = statement.executeQuery(selectTableSql)) {
                List<Message> messages = new ArrayList<>();
                while (rs.next()) {

                    Message message = new Message(rs.getInt("id"), rs.getString("sender"),
                            rs.getString("chatroom"), rs.getString("body"),
                            rs.getTimestamp("time1").toLocalDateTime());
                    messages.add(message);

                }
                return messages;

            } catch (SQLException e) {
                throw new RepositoryException("ResultSet error", e);
            } finally {
                statement.close();
            }
        } catch (SQLException e) {
            throw new RepositoryException("Message reading error", e);
        }
    }
}
