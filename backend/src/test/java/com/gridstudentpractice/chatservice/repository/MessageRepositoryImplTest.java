package com.gridstudentpractice.chatservice.repository;


import com.gridstudentpractice.chatservice.model.ChatroomDto;
import com.gridstudentpractice.chatservice.model.MessageDto;
import com.gridstudentpractice.chatservice.model.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ActiveProfiles({"test","orm"})
public class MessageRepositoryImplTest {

    @Autowired
    private Connection connection;

    @Autowired
    private MessageRepository messageRepository;

    private static final String insertMessagesQuery = "INSERT INTO messages (id, sender, body, time1, chatroom) " +
            "VALUES (1, 1, 'body1', now(), 1), (2, 2, 'body2', now(), 2), (3, 3, 'body3', now(), 3);";
    private static final String selectMessagesQuery = "SELECT * FROM messages;";
    private static final String insertIntoRequiredTablesQuery = "INSERT INTO users VALUES (1, 'foo1', 'pass1'), (2, 'foo2', 'pass2'), (3, 'foo3', 'pass3'); " +
            "INSERT INTO chatrooms VALUES (1, 'chat1', 'desc1'), (2, 'chat2', 'desc2'), (3, 'chat3', 'desc3');";
    private static final String selectUsersQuery = "SELECT * FROM users;";
    private static final String selectChatroomsQuery = "SELECT * FROM chatrooms;";

    @Before
    public void before() throws SQLException, IOException {
        Statement statement = connection.createStatement();
        BufferedReader in = new BufferedReader(new FileReader("src/main/resources/clearH2Tables.sql"));
        String str;
        StringBuilder sb = new StringBuilder();
        while ((str = in.readLine()) != null) {
            sb.append(str).append("\n");
        }
        in.close();
        statement.executeUpdate(sb.toString());
        statement.executeUpdate(insertIntoRequiredTablesQuery);
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
        Statement statement = connection.createStatement();
        statement.executeUpdate(insertMessagesQuery);

        List<MessageDto> repoMessageDtos = messageRepository.getMessages();

        ResultSet rs1 = statement.executeQuery(selectMessagesQuery);
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

        assertEquals(testMessageDtos.size(), repoMessageDtos.size());

        ResultSet rs2 = statement.executeQuery(selectUsersQuery);
        List<UserDto> testUserDtos = new ArrayList<>();
        while (rs2.next()) {
            UserDto userDto = UserDto.builder()
                    .id(rs2.getInt("id"))
                    .login(rs2.getString("login"))
                    .password(rs2.getString("password"))
                    .build();
            testUserDtos.add(userDto);
        }

        ResultSet rs3 = statement.executeQuery(selectChatroomsQuery);
        List<ChatroomDto> testChatroomDtos = new ArrayList<>();
        while (rs3.next()) {
            ChatroomDto chatroomDto = ChatroomDto.builder()
                    .id(rs3.getInt("id"))
                    .name(rs3.getString("name"))
                    .description(rs3.getString("description"))
                    .build();
            testChatroomDtos.add(chatroomDto);
        }
        statement.close();

        for (int i = 0; i < repoMessageDtos.size(); i++) {
            assertEquals(testMessageDtos.get(i).getId(), repoMessageDtos.get(i).getId());
            assertEquals(findUserById(Integer.parseInt(testMessageDtos.get(i).getSender()), testUserDtos).getLogin(),
                    repoMessageDtos.get(i).getSender());
            assertEquals(testMessageDtos.get(i).getBody(), repoMessageDtos.get(i).getBody());
            assertEquals(testMessageDtos.get(i).getTimestamp().withNano(0), repoMessageDtos.get(i).getTimestamp().withNano(0));
            assertEquals(findChatroomById(Integer.parseInt(testMessageDtos.get(i).getChatroom()), testChatroomDtos).getName(),
                    repoMessageDtos.get(i).getChatroom());
        }
    }

    @Test
    public void createMessage() throws SQLException {
        Statement statement = connection.createStatement();

        MessageDto messageDto = MessageDto.builder()
                .id(1)
                .sender("1")
                .body("body")
                .timestamp(LocalDateTime.now())
                .chatroom("1")
                .build();

        messageRepository.createMessage(messageDto);

        ResultSet rs = statement.executeQuery(selectMessagesQuery);
        if (rs.next()) {
            assertEquals(messageDto.getId(), rs.getInt("id"));
            assertEquals(messageDto.getSender(), rs.getString("sender"));
            assertEquals(messageDto.getBody(), rs.getString("body"));
            assertEquals(messageDto.getChatroom(), rs.getString("chatroom"));
        }
        statement.close();
    }

    @Test
    public void updateMessage() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(insertMessagesQuery);

        MessageDto foo = MessageDto.builder()
                .id(1)
                .sender("1")
                .body("fooBody")
                .timestamp(LocalDateTime.now())
                .chatroom("1")
                .build();

        messageRepository.updateMessage(foo);

        ResultSet rs = statement.executeQuery(selectMessagesQuery);
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

        assertEquals(foo.getId(), findMessageById(foo.getId(), messageDtos).getId());
        assertEquals(foo.getSender(), findMessageById(foo.getId(), messageDtos).getSender());
        assertEquals(foo.getBody(), findMessageById(foo.getId(), messageDtos).getBody());
        assertEquals(foo.getTimestamp().withNano(0), findMessageById(foo.getId(), messageDtos).getTimestamp().withNano(0));
        assertEquals(foo.getChatroom(), findMessageById(foo.getId(), messageDtos).getChatroom());
    }

    @Test
    public void deleteMessage() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(insertMessagesQuery);

        int messageId = 1;
        messageRepository.deleteMessageById(messageId);

        ResultSet rs = statement.executeQuery(selectMessagesQuery);
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
        statement.close();

        assertNull(findMessageById(messageId, messageDtos));
    }

}
