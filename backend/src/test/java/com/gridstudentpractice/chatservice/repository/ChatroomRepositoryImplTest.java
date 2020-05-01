package com.gridstudentpractice.chatservice.repository;

import com.gridstudentpractice.chatservice.model.ChatroomDto;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"test","orm"})
public class ChatroomRepositoryImplTest {

    @Autowired
    private Connection connection;

    @Autowired
    private ChatroomRepository chatroomRepository;

    private static final String clearChatroomTableQuery = "DELETE FROM chatrooms; " +
                                                            "ALTER TABLE chatrooms ALTER COLUMN id RESTART WITH 1";
    private static final String insertChatroomQuery = "INSERT INTO chatrooms VALUES (1, 'chatroom', 'description1'), " +
                                                                            "(2, 'chatroom', 'description2'), " +
                                                                            "(3, 'chatroom3', 'description3');";
    private static final String getChatrooms = "SELECT * FROM chatrooms ";
    private static final String insertOneChatroomQuery = "INSERT INTO chatrooms VALUES (1, 'chatroom', 'description1'); ";
    private static final String insertOneUserQuery = "INSERT INTO users VALUES (1, 'foo1', 'pass1'); ";
    private static final String getUserChatroom = "SELECT * FROM user_chatroom ";
    private static final String clearUserChatroomQuery = "DELETE FROM user_chatroom CASCADE";

    @After
    public void after() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(clearChatroomTableQuery);
        statement.close();
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
    public void getChatroomById() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(insertChatroomQuery);
        int chatroomId = 3;
        ChatroomDto chatroomDto = chatroomRepository.getChatroomById(chatroomId);

        ResultSet rs = statement.executeQuery(getChatrooms);
        List<ChatroomDto> chatroomDtos = new ArrayList<>();
        while (rs.next()) {
            ChatroomDto chatroomDto1 = ChatroomDto.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .description(rs.getString("description"))
                    .build();
            chatroomDtos.add(chatroomDto1);
        }
        statement.close();

        assertEquals(findChatroomById(chatroomId, chatroomDtos).getId(), chatroomDto.getId());
        assertEquals(findChatroomById(chatroomId, chatroomDtos).getName(), chatroomDto.getName());
        assertEquals(findChatroomById(chatroomId, chatroomDtos).getDescription(), chatroomDto.getDescription());
    }

    @Test
    public void getChatroomsByName() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(insertChatroomQuery);
        statement.close();

        List<ChatroomDto> chatroomDtos = chatroomRepository.getChatroomsByName("chatroom");

        assertEquals(chatroomDtos.get(0).getId(), 1);
        assertEquals(chatroomDtos.get(0).getName(), "chatroom");
        assertEquals(chatroomDtos.get(0).getDescription(), "description1");

        assertEquals(chatroomDtos.get(1).getId(), 2);
        assertEquals(chatroomDtos.get(1).getName(), "chatroom");
        assertEquals(chatroomDtos.get(1).getDescription(), "description2");
    }

    @Test
    public void createChatroom() throws SQLException {

        ChatroomDto chatroomDto = ChatroomDto.builder()
                .id(1)
                .name("chatroomDto")
                .description("Description1")
                .build();

        chatroomRepository.createChatroom(chatroomDto);

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(getChatrooms);
        if (rs.next()) {
            assertEquals(chatroomDto.getId(), rs.getInt("id"));
            assertEquals(chatroomDto.getDescription(), rs.getString("description"));
            assertEquals(chatroomDto.getName(), rs.getString("name"));
        }
        statement.close();

    }

    @Test
    public void updateChatroom() throws SQLException {

        Statement statement = connection.createStatement();
        statement.executeUpdate(insertOneChatroomQuery);

        ChatroomDto chatroomDto = ChatroomDto.builder()
                .description("updatedDescription")
                .id(1)
                .name("updatedChatroom")
                .build();

        chatroomRepository.updateChatroom(chatroomDto);

        ResultSet rs = statement.executeQuery(getChatrooms);
        List<ChatroomDto> chatroomDtos = new ArrayList<>();
        while (rs.next()) {
            ChatroomDto chatroomDto1 = ChatroomDto.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .description(rs.getString("description"))
                    .build();
            chatroomDtos.add(chatroomDto1);
        }
        statement.close();

        assertEquals(chatroomDto.getId(), findChatroomById(chatroomDto.getId(), chatroomDtos).getId());
        assertEquals(chatroomDto.getName(), findChatroomById(chatroomDto.getId(), chatroomDtos).getName());
        assertEquals(chatroomDto.getDescription(), findChatroomById(chatroomDto.getId(), chatroomDtos).getDescription());
    }

    @Test
    public void deleteChatroomById() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(insertChatroomQuery);

        chatroomRepository.deleteChatroomById(1);

        ResultSet rs = statement.executeQuery(getChatrooms);

        List<ChatroomDto> chatroomDtos = new ArrayList<>();
        while (rs.next()) {
            ChatroomDto chatroomDto = ChatroomDto.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .description(rs.getString("description"))
                    .build();
            chatroomDtos.add(chatroomDto);
        }
        statement.close();

        assertNull(findChatroomById(1, chatroomDtos));

    }

    @Test
    public void addUserToChatroom() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(insertOneChatroomQuery);
        statement.executeUpdate(insertOneUserQuery);

        chatroomRepository.addUserToChatroom(1,1);

        ResultSet rs = statement.executeQuery(getUserChatroom);
        if(rs.next()) {
            assertEquals(rs.getInt("user_id"), 1);
            assertEquals(rs.getInt("chatroom_id"), 1);
        }
        statement.executeUpdate(clearUserChatroomQuery);
        statement.close();
    }
}
