package com.gridstudentpractice.chatservice.repository;


import com.gridstudentpractice.chatservice.model.ChatroomDto;
import com.gridstudentpractice.chatservice.model.MessageDto;
import com.gridstudentpractice.chatservice.model.UserDto;
import org.bitbucket.radistao.test.annotation.AfterAllMethods;
import org.bitbucket.radistao.test.annotation.BeforeAllMethods;
import org.bitbucket.radistao.test.runner.BeforeAfterSpringTestRunner;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
@ActiveProfiles({"test","jdbc"})
public class JDBCMessageRepositoryImplTest {

    @Autowired
    private Connection connection;

    @Autowired
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

    private MessageDto findMessageById(int id, List<MessageDto> messageDtos) {
        for (MessageDto messageDto : messageDtos) {
            if (messageDto.getId() == id) {
                return messageDto;
            }
        }
        return null;
    }

    private UserDto findUserById(int id, List<UserDto> userDtos) {
        for (UserDto userDto : userDtos) {
            if (userDto.getId() == id) {
                return userDto;
            }
        }
        return null;
    }

    private ChatroomDto findChatroomById(int id, List<ChatroomDto> chatroomDtos) {
        for (ChatroomDto chatroomDto : chatroomDtos) {
            if (chatroomDto.getId() == id) {
                return chatroomDto;
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

        List<MessageDto> repoMessageDtos = messageRepository.getMessages();

        Statement statement3 = connection.createStatement();
        ResultSet rs1 = statement3.executeQuery(selectMessagesQuery);
        List<MessageDto> testMessageDtos = new ArrayList<>();
        while (rs1.next()) {
            MessageDto messageDto = MessageDto.builder()
                    .id(rs1.getInt("id"))
                    .sender(rs1.getString("sender"))
                    .body(rs1.getString("body"))
                    .timestamp(rs1.getTimestamp("time1").toLocalDateTime())
                    .chatroom(rs1.getString("chatroom"))
                    .build();
            testMessageDtos.add(messageDto);
        }
        statement3.close();

        assertEquals(testMessageDtos.size(), repoMessageDtos.size());

        Statement statement4 = connection.createStatement();
        ResultSet rs2 = statement4.executeQuery(selectUsersQuery);
        List<UserDto> testUserDtos = new ArrayList<>();
        while (rs2.next()) {
            UserDto userDto = UserDto.builder()
                    .id(rs2.getInt("id"))
                    .login(rs2.getString("login"))
                    .password(rs2.getString("password"))
                    .build();
            testUserDtos.add(userDto);
        }
        statement4.close();

        Statement statement5 = connection.createStatement();
        ResultSet rs3 = statement5.executeQuery(selectChatroomsQuery);
        List<ChatroomDto> testChatroomDtos = new ArrayList<>();
        while (rs3.next()) {
            ChatroomDto chatroomDto = ChatroomDto.builder()
                    .id(rs3.getInt("id"))
                    .name(rs3.getString("name"))
                    .description(rs3.getString("description"))
                    .build();
            testChatroomDtos.add(chatroomDto);
        }
        statement5.close();

        for (int i = 0; i < repoMessageDtos.size(); i++) {
            assertEquals(testMessageDtos.get(i).getId(), repoMessageDtos.get(i).getId());
            assertEquals(findUserById(Integer.parseInt(testMessageDtos.get(i).getSender()), testUserDtos).getLogin(),
                    repoMessageDtos.get(i).getSender());
            assertEquals(testMessageDtos.get(i).getBody(), repoMessageDtos.get(i).getBody());
            assertEquals(testMessageDtos.get(i).getTimestamp().withNano(0), repoMessageDtos.get(i).getTimestamp().withNano(0));
            assertEquals(findChatroomById(Integer.parseInt(testMessageDtos.get(i).getChatroom()), testChatroomDtos).getName(),
                    repoMessageDtos.get(i).getChatroom());
        }

        Statement statement6 = connection.createStatement();
        statement6.executeUpdate(dropRequiredTablesQuery);
        statement6.close();
    }

    @Test
    public void createMessage() throws SQLException {
        MessageDto messageDto = MessageDto.builder()
                .id(1)
                .sender("1")
                .body("body")
                .timestamp(LocalDateTime.now())
                .chatroom("1")
                .build();

        messageRepository.createMessage(messageDto);

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(selectMessagesQuery);
        if (rs.next()) {
            assertEquals(messageDto.getId(), rs.getInt("id"));
            assertEquals(messageDto.getSender(), rs.getString("sender"));
            assertEquals(messageDto.getBody(), rs.getString("body"));
            assertEquals(messageDto.getChatroom(), rs.getString("chatroom"));
            assertEquals(messageDto.getTimestamp().withNano(0), rs.getTimestamp("time1").toLocalDateTime().withNano(0));
        }
        statement.close();
    }

    @Test
    public void updateMessage() throws SQLException {
        Statement statement1 = connection.createStatement();
        statement1.executeUpdate(insertMessagesQuery);
        statement1.close();

        MessageDto foo = MessageDto.builder()
                .id(1)
                .sender("1")
                .body("fooBody")
                .timestamp(LocalDateTime.now())
                .chatroom("1")
                .build();

        messageRepository.updateMessage(foo);

        Statement statement2 = connection.createStatement();
        ResultSet rs = statement2.executeQuery(selectMessagesQuery);
        List<MessageDto> messageDtos = new ArrayList<>();
        while (rs.next()) {
            MessageDto messageDto = MessageDto.builder()
                    .id(rs.getInt("id"))
                    .sender(rs.getString("sender"))
                    .body(rs.getString("body"))
                    .timestamp(rs.getTimestamp("time1").toLocalDateTime())
                    .chatroom(rs.getString("chatroom"))
                    .build();
            messageDtos.add(messageDto);
        }
        statement2.close();

        assertEquals(foo.getId(), findMessageById(foo.getId(), messageDtos).getId());
        assertEquals(foo.getSender(), findMessageById(foo.getId(), messageDtos).getSender());
        assertEquals(foo.getBody(), findMessageById(foo.getId(), messageDtos).getBody());
        assertEquals(foo.getTimestamp().withNano(0), findMessageById(foo.getId(), messageDtos).getTimestamp().withNano(0));
        assertEquals(foo.getChatroom(), findMessageById(foo.getId(), messageDtos).getChatroom());
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
        List<MessageDto> messageDtos = new ArrayList<>();
        while (rs.next()) {
            MessageDto messageDto = MessageDto.builder()
                    .id(rs.getInt("id"))
                    .sender(rs.getString("sender"))
                    .body(rs.getString("body"))
                    .timestamp(rs.getTimestamp("time1").toLocalDateTime())
                    .chatroom(rs.getString("chatroom"))
                    .build();
            messageDtos.add(messageDto);
        }
        statement2.close();

        assertNull(findMessageById(messageId, messageDtos));
    }

}
