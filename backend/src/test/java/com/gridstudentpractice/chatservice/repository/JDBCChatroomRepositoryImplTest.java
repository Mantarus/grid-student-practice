package com.gridstudentpractice.chatservice.repository;

import com.gridstudentpractice.chatservice.model.ChatroomDto;

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

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(BeforeAfterSpringTestRunner.class)
@SpringBootTest
@ActiveProfiles({"test","jdbc"})
public class JDBCChatroomRepositoryImplTest {

    @Autowired
    private Connection connection;

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
    private static final String clearChatroomTableQuery = "DELETE FROM chatrooms; " +
                                                            "ALTER TABLE chatrooms ALTER COLUMN id RESTART WITH 1";
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

    @BeforeAllMethods
    public void beforeAll() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(createChatroomTableQuery);
        statement.close();
    }

    @After
    public void after() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(clearChatroomTableQuery);
        statement.close();
    }

    @AfterAllMethods
    public void afterAll() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(dropChatroomTableQuery);
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
        statement.close();

        int chatroomId = 3;
        ChatroomDto chatroomDto = chatroomRepository.getChatroomById(chatroomId);

        Statement statement1 = connection.createStatement();
        ResultSet rs = statement1.executeQuery(getChatrooms);
        List<ChatroomDto> chatroomDtos = new ArrayList<>();
        while (rs.next()) {
            ChatroomDto chatroomDto1 = ChatroomDto.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .description(rs.getString("description"))
                    .build();
            chatroomDtos.add(chatroomDto1);
        }
        statement1.close();

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
        statement.close();

        ChatroomDto chatroomDto = ChatroomDto.builder()
                .description("updatedDescription")
                .id(1)
                .name("updatedChatroom")
                .build();

        chatroomRepository.updateChatroom(chatroomDto);

        Statement statement1 = connection.createStatement();
        ResultSet rs = statement1.executeQuery(getChatrooms);
        List<ChatroomDto> chatroomDtos = new ArrayList<>();
        while (rs.next()) {
            ChatroomDto chatroomDto1 = ChatroomDto.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .description(rs.getString("description"))
                    .build();
            chatroomDtos.add(chatroomDto1);
        }
        statement1.close();

        assertEquals(chatroomDto.getId(), findChatroomById(chatroomDto.getId(), chatroomDtos).getId());
        assertEquals(chatroomDto.getName(), findChatroomById(chatroomDto.getId(), chatroomDtos).getName());
        assertEquals(chatroomDto.getDescription(), findChatroomById(chatroomDto.getId(), chatroomDtos).getDescription());
    }

    @Test
    public void deleteChatroomById() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(insertChatroomQuery);
        statement.close();

        chatroomRepository.deleteChatroomById(1);

        Statement statement1 = connection.createStatement();
        ResultSet rs = statement1.executeQuery(getChatrooms);

        List<ChatroomDto> chatroomDtos = new ArrayList<>();
        while (rs.next()) {
            ChatroomDto chatroomDto = ChatroomDto.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .description(rs.getString("description"))
                    .build();
            chatroomDtos.add(chatroomDto);
        }
        statement1.close();

        assertNull(findChatroomById(1, chatroomDtos));

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
