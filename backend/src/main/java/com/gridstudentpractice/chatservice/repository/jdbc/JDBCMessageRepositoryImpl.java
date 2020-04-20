package com.gridstudentpractice.chatservice.repository.jdbc;

import com.gridstudentpractice.chatservice.exception.RepositoryException;
import com.gridstudentpractice.chatservice.model.MessageDto;
import com.gridstudentpractice.chatservice.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Profile("jdbc")
@Repository
public class JDBCMessageRepositoryImpl implements MessageRepository {

    @Autowired
    private Connection connection;

    final static private String insertTableSql = "INSERT INTO messages (sender, body, chatroom) VALUES (?::integer, ?, ?::integer)";
    final static private String selectTableSql = "SELECT m.id AS id, " +
                                                "u.login AS sender, " +
                                                "m.body AS body, " +
                                                "m.time1 AS time1, " +
                                                "ch.name AS chatroom " +
            "FROM messages m " +
            "JOIN users u ON u.id=m.sender " +
            "JOIN chatrooms ch ON  ch.id=m.chatroom";
    final static private String updateMessage = "UPDATE messages m SET body = ? WHERE m.id = ?";
    final static private String deleteMessage = "DELETE FROM messages m WHERE m.id = ?";

    @Override
    public void createMessage(MessageDto messageDto) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertTableSql)) {

            preparedStatement.setString(1, messageDto.getSender());
            preparedStatement.setString(2, messageDto.getBody());
            preparedStatement.setString(3, messageDto.getChatroom());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException("MessageDto creation error", e);
        }
    }

    @Override
    public List<MessageDto> getMessages() {
        try (Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery(selectTableSql)) {
                List<MessageDto> messageDtos = new ArrayList<>();
                while (rs.next()) {

                    MessageDto messageDto = MessageDto.builder()
                            .id(rs.getInt("id"))
                            .sender(rs.getString("sender"))
                            .chatroom(rs.getString("chatroom"))
                            .body( rs.getString("body"))
                            .timestamp(rs.getTimestamp("time1").toLocalDateTime())
                            .build();
                    messageDtos.add(messageDto);

                }
                return messageDtos;

            } catch (SQLException e) {
                throw new RepositoryException("ResultSet error", e);
            }
        } catch (SQLException e) {
            throw new RepositoryException("MessageDto reading error", e);
        }
    }

    @Override
    public void updateMessage(MessageDto messageDto) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateMessage)) {

            preparedStatement.setString(1, messageDto.getBody());
            preparedStatement.setInt(2, messageDto.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException("MessageDto update error", e);
        }
    }

    @Override
    public void deleteMessageById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteMessage)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException("MessageDto delete error", e);
        }
    }
}
