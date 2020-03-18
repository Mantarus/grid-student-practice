package com.gridstudentpractice.chatservice.repository;

import com.gridstudentpractice.chatservice.exception.RepositoryException;
import com.gridstudentpractice.chatservice.model.Chatroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JDBCChatroomRepositoryImpl implements ChatroomRepository {

    @Autowired
    private Connection connection;

    final static private String createChatroom = "INSERT INTO chatrooms (name, description) VALUES (?, ?)";
    final static private String getChatroomById = "SELECT c.* FROM chatrooms c WHERE c.id = ?";
    final static private String getChatroomByName = "SELECT c.* FROM chatrooms c WHERE c.name = ? ORDER BY c.id";
    final static private String createUserInChatroom = "INSERT INTO user_chatroom (user_id, chatroom_id) VALUES (?, ?)";
    final static private String updateChatroom = "UPDATE chatrooms c SET name = ?, description = ? WHERE c.id = ?";
    final static private String deleteChatroom = "DELETE FROM chatrooms c WHERE c.id = ?";



    @Override
    public void createChatroom(Chatroom chatroom) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(createChatroom)) {

            preparedStatement.setString(1, chatroom.getName());
            preparedStatement.setString(2, chatroom.getDescription());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException("Chatroom creation error", e);
        }
    }

    @Override
    public Chatroom getChatroomById(int chatroomId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getChatroomById)) {
            preparedStatement.setInt(1, chatroomId);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return Chatroom.builder()
                            .id(rs.getInt("id"))
                            .name(rs.getString("name"))
                            .description(rs.getString("description"))
                            .build();
                } else throw new RepositoryException("No such chatroom");
            } catch (SQLException e) {
                throw new RepositoryException("ResultSet error", e);
            }
        } catch (SQLException e) {
            throw new RepositoryException("Chatroom reading error", e);
        }
    }

    @Override
    public List<Chatroom> getChatroomsByName(String chatroomName) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getChatroomByName)) {
            preparedStatement.setString(1, chatroomName);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                List<Chatroom> chatrooms = new ArrayList<>();
                while (rs.next()) {
                    Chatroom chatroom = Chatroom.builder()
                            .id(rs.getInt("id"))
                            .name(rs.getString("name"))
                            .description(rs.getString("description"))
                            .build();
                    chatrooms.add(chatroom);
                }
                return chatrooms;

            } catch (SQLException e) {
                throw new RepositoryException("ResultSet error", e);
            }
        } catch (SQLException e) {
            throw new RepositoryException("Chatroom reading error", e);
        }
    }

    @Override
    public void updateChatroom(Chatroom chatroom) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateChatroom)) {

            preparedStatement.setString(1, chatroom.getName());
            preparedStatement.setString(2, chatroom.getDescription());
            preparedStatement.setInt(3, chatroom.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException("Chatroom update error", e);
        }
    }

    @Override
    public void deleteChatroomById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteChatroom)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException("Chatroom delete error", e);
        }
    }


    //TODO: not working yet
    @Override
    public void addUserToChatroom(int uId, int cId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(createUserInChatroom)) {

            preparedStatement.setInt(1, uId);
            preparedStatement.setInt(2, cId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException("Chatroom creation error", e);
        }
    }

}
