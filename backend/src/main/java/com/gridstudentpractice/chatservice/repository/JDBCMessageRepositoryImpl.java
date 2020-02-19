package com.gridstudentpractice.chatservice.repository;

import com.gridstudentpractice.chatservice.DbUtil;
import com.gridstudentpractice.chatservice.exception.RepositoryException;
import com.gridstudentpractice.chatservice.model.Message;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JDBCMessageRepositoryImpl implements MessageRepository {

    private List<Message> messages = new ArrayList<>();

    final static String insertTableSql = "INSERT INTO messages (sender, body, time1) VALUES (\'?\', \'?\',\'?\')" ;
    final static String selectTableSql = "SELECT sender, body, time1 from messages";

    @Override
    public boolean createMessage(Message message) {

        String mSender = message.getSender();
        String mBody = message.getBody();
        String mTime = message.getTimestamp().toString();

        try (PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(insertTableSql)) {

            preparedStatement.setString(1, mSender);
            preparedStatement.setString(2, mBody);
            preparedStatement.setString(3, mTime);

            preparedStatement.executeUpdate();
            if(preparedStatement.executeUpdate() == 0) return false;
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RepositoryException("Message creation error", e);
        }
        return true;
    }

    @Override
    public List<Message> readMessages() {
        try (Statement statement = DbUtil.getConnection().createStatement()) {
            try (ResultSet rs = statement.executeQuery(selectTableSql)) {
                while (rs.next()) {

                    String sender = rs.getString("sender");
                    String body = rs.getString("body");
                    String time1 = rs.getString("time1");

                    Message message = new Message();
                    message.setSender(sender);
                    message.setBody(body);
                    message.setTimestamp(LocalDateTime.parse(time1));
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
