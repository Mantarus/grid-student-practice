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

    final static String insertTableSql = "INSERT INTO messages (sender, body) VALUES (?, ?)";
    final static String selectTableSql = "SELECT * FROM messages";

    @Override
    public boolean createMessage(Message message) {

        try (PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(insertTableSql)) {

            preparedStatement.setString(1, message.getSender());
            preparedStatement.setString(2, message.getBody());

            if (preparedStatement.executeUpdate() == 0) {
                return false;
            }
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RepositoryException("Message creation error", e);
        }
        return true;
    }

    @Override
    public List<Message> getMessages() {
        try (Statement statement = DbUtil.getConnection().createStatement()) {
            try (ResultSet rs = statement.executeQuery(selectTableSql)) {
                List<Message> messages = new ArrayList<>();
                while (rs.next()) {

                    Message message = new Message(rs.getInt("id"), rs.getString("sender"),
                            rs.getString("body"), rs.getTimestamp("time1").toLocalDateTime());
                    messages.add(message);

                }
                statement.close();
                return messages;

            } catch (SQLException e) {
                throw new RepositoryException("ResultSet error", e);
            }
        } catch (SQLException e) {
            throw new RepositoryException("Message reading error", e);
        }
    }
}
