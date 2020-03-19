package com.gridstudentpractice.chatservice.chatroom;

import com.gridstudentpractice.chatservice.model.Chatroom;

import com.gridstudentpractice.chatservice.repository.ChatroomRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


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
    private static final String insertUsersQuery = "INSERT INTO chatrooms VALUES (1, 'foo1', 'pass1'), " +
                                                                            "(2, 'foo2', 'pass2'), " +
                                                                            "(3, 'foo3', 'pass3');";

    @Before
    public void beforeAll() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(createChatroomTableQuery);
        statement.close();
    }

    @After
    public void afterAll() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(dropChatroomTableQuery);
        statement.close();
    }

    @AfterEach
    public void afterEach() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeQuery(clearChatroomTableQuery);
        statement.close();
    }

    @Test
    public void createChatroom() {

        Chatroom chatroom = Chatroom.builder()
                            .description("Description1")
                            .id(1)
                            .name("chatroom1")
                            .build();

        chatroomRepository.createChatroom(chatroom);


    }

    @Test
    public void getChatroomById() {

    }

    @Test
    public void getChatroomsByName() {

    }

    @Test
    public void updateChatroom() {

    }

    @Test
    public void deleteChatroomById() {

    }

    @Test
    public void addUserToChatroom() {

    }


}
