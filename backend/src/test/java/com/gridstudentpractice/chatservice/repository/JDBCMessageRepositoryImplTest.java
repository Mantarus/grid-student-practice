package com.gridstudentpractice.chatservice.repository;


import com.gridstudentpractice.chatservice.model.Chatroom;
import com.gridstudentpractice.chatservice.model.Message;
import com.gridstudentpractice.chatservice.model.User;
import org.bitbucket.radistao.test.annotation.AfterAllMethods;
import org.bitbucket.radistao.test.annotation.BeforeAllMethods;
import org.bitbucket.radistao.test.runner.BeforeAfterSpringTestRunner;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(BeforeAfterSpringTestRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class JDBCMessageRepositoryImplTest {

    @Autowired
    private Connection connection;

    @Autowired
    @Qualifier("JDBCMessageRepositoryImpl")
    private MessageRepository messageRepository;

    private static final String createMessageTableQuery = "CREATE TABLE messages " +
                                                        "( " +
                                                        "id INT NOT NULL AUTO_INCREMENT," +
                                                        "sender INT NOT NULL, " +
                                                        "body TEXT NOT NULL, " +
                                                        "time1 TIMESTAMP DEFAULT NOW(), " +
                                                        "chatroom INT NOT NULL, " +
                                                        "PRIMARY KEY (id) " +
                                                        ");";
    private static final String dropMessageTableQuery = "DROP TABLE messages;";
    private static final String clearMessageTableQuery = "DELETE FROM messages; " +
                                                            "ALTER TABLE messages ALTER COLUMN id RESTART WITH 1;";
    private static final String insertMessagesQuery = "INSERT INTO messages(id, sender, body, chatroom) " +
                                                        "VALUES (1, 1, 'body1', 1), " +
                                                        "(2, 2, 'body2', 2), " +
                                                        "(3, 3, 'body3', 3);";
    private static final String selectMessagesQuery = "SELECT * FROM messages;";
    private static final String createRequiredTablesQuery = "CREATE TABLE users (id INT AUTO_INCREMENT, login TEXT NOT NULL, " +
            "password TEXT NOT NULL, PRIMARY KEY (id)); " +
            "CREATE TABLE chatrooms (id INT AUTO_INCREMENT, name TEXT NOT NULL, description TEXT, PRIMARY KEY (id)); " +
            "INSERT INTO users VALUES (1, 'foo1', 'pass1'), (2, 'foo2', 'pass2'), (3, 'foo3', 'pass3'); " +
            "INSERT INTO chatrooms VALUES (1, 'chat1', 'desc1'), (2, 'chat2', 'desc2'), (3, 'chat3', 'desc3');";
    private static final String dropRequiredTablesQuery = "DROP TABLE users, chatrooms;";
    private static final String selectUsersQuery = "SELECT * FROM users;";
    private static final String selectChatroomsQuery = "SELECT * FROM chatrooms;";

    @BeforeAllMethods
    public void beforeAll() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(createMessageTableQuery);
        statement.close();
    }

    @After
    public void after() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(clearMessageTableQuery);
        statement.close();
    }

    @AfterAllMethods
    public void afterAll() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(dropMessageTableQuery);
        statement.close();
    }

    private Message findMessageById(int id, List<Message> messages) {
        for (Message message : messages) {
            if (message.getId() == id) {
                return message;
            }
        }
        return null;
    }

    private User findUserById(int id, List<User> users) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
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
    public void getAllMessages() throws SQLException {
        Statement statement1 = connection.createStatement();
        statement1.executeUpdate(insertMessagesQuery);
        statement1.close();
        Statement statement2 = connection.createStatement();
        statement2.executeUpdate(createRequiredTablesQuery);
        statement2.close();

        List<Message> repoMessages = messageRepository.getMessages();

        Statement statement3 = connection.createStatement();
        ResultSet rs1 = statement3.executeQuery(selectMessagesQuery);
        List<Message> testMessages = new ArrayList<>();
        while (rs1.next()) {
            Message message = Message.builder()
                    .id(rs1.getInt("id"))
                    .sender(rs1.getString("sender"))
                    .body(rs1.getString("body"))
                    .timestamp(rs1.getTimestamp("time1").toLocalDateTime())
                    .chatroom(rs1.getString("chatroom"))
                    .build();
            testMessages.add(message);
        }
        statement3.close();

        assertEquals(testMessages.size(), repoMessages.size());

        Statement statement4 = connection.createStatement();
        ResultSet rs2 = statement4.executeQuery(selectUsersQuery);
        List<User> testUsers = new ArrayList<>();
        while (rs2.next()) {
            User user = User.builder()
                    .id(rs2.getInt("id"))
                    .login(rs2.getString("login"))
                    .password(rs2.getString("password"))
                    .build();
            testUsers.add(user);
        }
        statement4.close();

        Statement statement5 = connection.createStatement();
        ResultSet rs3 = statement5.executeQuery(selectChatroomsQuery);
        List<Chatroom> testChatrooms = new ArrayList<>();
        while (rs3.next()) {
            Chatroom chatroom = Chatroom.builder()
                    .id(rs3.getInt("id"))
                    .name(rs3.getString("name"))
                    .description(rs3.getString("description"))
                    .build();
            testChatrooms.add(chatroom);
        }
        statement5.close();

        for (int i = 0; i < repoMessages.size(); i++) {
            assertEquals(testMessages.get(i).getId(), repoMessages.get(i).getId());
            assertEquals(findUserById(Integer.parseInt(testMessages.get(i).getSender()), testUsers).getLogin(),
                    repoMessages.get(i).getSender());
            assertEquals(testMessages.get(i).getBody(), repoMessages.get(i).getBody());
            assertEquals(testMessages.get(i).getTimestamp().withNano(0), repoMessages.get(i).getTimestamp().withNano(0));
            assertEquals(findChatroomById(Integer.parseInt(testMessages.get(i).getChatroom()), testChatrooms).getName(),
                    repoMessages.get(i).getChatroom());
        }

        Statement statement6 = connection.createStatement();
        statement6.executeUpdate(dropRequiredTablesQuery);
        statement6.close();
    }

    @Test
    public void createMessage() throws SQLException {
        Message message = Message.builder()
                .id(1)
                .sender("1")
                .body("body")
                .timestamp(LocalDateTime.now())
                .chatroom("1")
                .build();

        messageRepository.createMessage(message);

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(selectMessagesQuery);
        if (rs.next()) {
            assertEquals(message.getId(), rs.getInt("id"));
            assertEquals(message.getSender(), rs.getString("sender"));
            assertEquals(message.getBody(), rs.getString("body"));
            assertEquals(message.getChatroom(), rs.getString("chatroom"));
            assertEquals(message.getTimestamp().withNano(0), rs.getTimestamp("time1").toLocalDateTime().withNano(0));
        }
        statement.close();
    }

    @Test
    public void updateMessage() throws SQLException {
        Statement statement1 = connection.createStatement();
        statement1.executeUpdate(insertMessagesQuery);
        statement1.close();

        Message foo = Message.builder()
                .id(1)
                .sender("1")
                .body("fooBody")
                .timestamp(LocalDateTime.now())
                .chatroom("1")
                .build();

        messageRepository.updateMessage(foo);

        Statement statement2 = connection.createStatement();
        ResultSet rs = statement2.executeQuery(selectMessagesQuery);
        List<Message> messages = new ArrayList<>();
        while (rs.next()) {
            Message message = Message.builder()
                    .id(rs.getInt("id"))
                    .sender(rs.getString("sender"))
                    .body(rs.getString("body"))
                    .timestamp(rs.getTimestamp("time1").toLocalDateTime())
                    .chatroom(rs.getString("chatroom"))
                    .build();
            messages.add(message);
        }
        statement2.close();

        assertEquals(foo.getId(), findMessageById(foo.getId(), messages).getId());
        assertEquals(foo.getSender(), findMessageById(foo.getId(), messages).getSender());
        assertEquals(foo.getBody(), findMessageById(foo.getId(), messages).getBody());
        assertEquals(foo.getTimestamp().withNano(0), findMessageById(foo.getId(), messages).getTimestamp().withNano(0));
        assertEquals(foo.getChatroom(), findMessageById(foo.getId(), messages).getChatroom());
    }

    @Test
    public void deleteUser() throws SQLException {
        Statement statement1 = connection.createStatement();
        statement1.executeUpdate(insertMessagesQuery);
        statement1.close();

        int messageId = 1;
        messageRepository.deleteMessageById(messageId);

        Statement statement2 = connection.createStatement();
        ResultSet rs = statement2.executeQuery(selectMessagesQuery);
        List<Message> messages = new ArrayList<>();
        while (rs.next()) {
            Message message = Message.builder()
                    .id(rs.getInt("id"))
                    .sender(rs.getString("sender"))
                    .body(rs.getString("body"))
                    .timestamp(rs.getTimestamp("time1").toLocalDateTime())
                    .chatroom(rs.getString("chatroom"))
                    .build();
            messages.add(message);
        }
        statement2.close();

        assertNull(findMessageById(messageId, messages));
    }

}
