package com.gridstudentpractice.chatservice.repository;

import com.gridstudentpractice.chatservice.exception.RepositoryException;
import com.gridstudentpractice.chatservice.model.Chatroom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class JDBCChatroomRepositoryImplTest {

    private static Connection connection;

    @Autowired
    private Connection autowiredConnection;

    @PostConstruct
    public void init() {
        JDBCChatroomRepositoryImplTest.connection = this.autowiredConnection;
    }

    @Autowired
    private ChatroomRepository chatroomRepository;

    private static final String createChatroomTableQuery = "CREATE TABLE chatrooms " +
                                                            "( " +
                                                            "id INT NOT NULL AUTO_INCREMENT, " +
                                                            "name TEXT NOT NULL , " +
                                                            "description TEXT ," +
                                                            "PRIMARY KEY (id) " +
                                                            ");";
    private static final String dropChatroomTableQuery = "DROP TABLE chatrooms;";
    private static final String clearChatroomTableQuery = "DELETE FROM chatrooms;";
    private static final String insertChatroomQuery = "INSERT INTO chatrooms VALUES (1, 'chatroom', 'description1'), " +
                                                                            "(2, 'chatroom', 'description2'), " +
                                                                            "(3, 'chatroom3', 'description3');";
    private static final String getChatrooms = "SELECT * FROM chatrooms ";
    private static final String insertOneChatroomQuery = "INSERT INTO chatrooms VALUES (1, 'chatroom', 'description1')";
    private static final String createUserChatroomTableQuery = "CREATE TABLE user_chatroom " +
                                                            "( " +
                                                            "user_id INT NOT NULL , " +
                                                            "chatroom_id INT NOT NULL  " +
                                                            ");";
    private static final String getUserChatroom = "SELECT * FROM user_chatroom ";

    @Before
    public void before() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(createChatroomTableQuery);
        statement.close();
    }

    @After
    public void after() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(dropChatroomTableQuery);
        statement.close();
    }

    private Chatroom findChatroomById(int id, List<Chatroom> chatrooms) {
        for (Chatroom chatroom : chatrooms) {
            if (chatroom.getId() == id) {
                return chatroom;
            }
        }
        return null;
    }

    @Test
    public void createChatroom() throws SQLException {

        Chatroom chatroom = Chatroom.builder()
                            .description("Description1")
                            .id(1)
                            .name("chatroom1")
                            .build();

        chatroomRepository.createChatroom(chatroom);

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(getChatrooms);
        if (rs.next()) {
            assertEquals(chatroom.getId(), rs.getInt("id"));
            assertEquals(chatroom.getDescription(), rs.getString("description"));
            assertEquals(chatroom.getName(), rs.getString("name"));

        }
        statement.close();

    }

    @Test
    public void getChatroomById() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(insertChatroomQuery);

        Chatroom chatroom = chatroomRepository.getChatroomById(3);

        assertEquals(chatroom.getId(), 3);
        assertEquals(chatroom.getDescription(), "description3");
        assertEquals(chatroom.getName(), "chatroom3");

        statement.close();
    }

    @Test
    public void getChatroomsByName() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(insertChatroomQuery);

        List<Chatroom> chatrooms = chatroomRepository.getChatroomsByName("chatroom");

        assertEquals(chatrooms.get(0).getId(), 1);
        assertEquals(chatrooms.get(0).getName(), "chatroom");
        assertEquals(chatrooms.get(0).getDescription(), "description1");

        assertEquals(chatrooms.get(1).getId(), 2);
        assertEquals(chatrooms.get(1).getName(), "chatroom");
        assertEquals(chatrooms.get(1).getDescription(), "description2");

        statement.close();
    }

    @Test
    public void updateChatroom() throws SQLException {

        Statement statement = connection.createStatement();
        statement.executeUpdate(insertOneChatroomQuery);
        statement.close();

        Chatroom chatroom = Chatroom.builder()
                .description("updatedDescription")
                .id(1)
                .name("updatedChatroom")
                .build();

        chatroomRepository.updateChatroom(chatroom);

        Statement statement1 = connection.createStatement();
        ResultSet rs = statement1.executeQuery(getChatrooms);
        if (rs.next()) {
            assertEquals(chatroom.getId(), rs.getInt("id"));
            assertEquals(chatroom.getDescription(), rs.getString("description"));
            assertEquals(chatroom.getName(), rs.getString("name"));

        }
        statement1.close();
    }

    @Test
    public void deleteChatroomById() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(insertChatroomQuery);
        statement.close();

        chatroomRepository.deleteChatroomById(1);

        Statement statement1 = connection.createStatement();
        ResultSet rs = statement1.executeQuery(getChatrooms);

        List<Chatroom> chatrooms = new ArrayList<>();
        while (rs.next()) {
            Chatroom chatroom = Chatroom.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .description(rs.getString("description"))
                    .build();
            chatrooms.add(chatroom);
        }
        statement1.close();

        assertNull(findChatroomById(1,chatrooms));

    }

    @Test
    public void addUserToChatroom() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(createUserChatroomTableQuery);
        statement.close();

        chatroomRepository.addUserToChatroom(1,1);

        Statement statement1 = connection.createStatement();
        ResultSet rs = statement1.executeQuery(getUserChatroom);
        if(rs.next()) {
            assertEquals(rs.getInt("user_id"), 1);
            assertEquals(rs.getInt("chatroom_id"), 1);
        }

        statement1.close();
    }
}
