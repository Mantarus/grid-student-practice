package com.gridstudentpractice.chatservice.repository;

import com.gridstudentpractice.chatservice.model.User;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class JDBCUserRepositoryImplTest {

    private static Connection connection;

    @Autowired
    private Connection autowiredConnection;

    @PostConstruct
    public void init() {
        JDBCUserRepositoryImplTest.connection = this.autowiredConnection;
    }

    @Autowired
    private UserRepository userRepository;

    private static final String createUserTableQuery = "CREATE TABLE users " +
                                                        "( " +
                                                        "id INT NOT NULL AUTO_INCREMENT, " +
                                                        "login TEXT NOT NULL UNIQUE, " +
                                                        "password TEXT NOT NULL, " +
                                                        "PRIMARY KEY (id) " +
                                                        ");";
    private static final String dropUserTableQuery = "DROP TABLE users;";
    private static final String deleteFromUserQuery = "DELETE FROM users;";
    private static final String insertUsersQuery = "INSERT INTO users VALUES (1, 'foo1', 'pass1'), " +
                                                                            "(2, 'foo2', 'pass2'), " +
                                                                            "(3, 'foo3', 'pass3');";

    @BeforeAll
    public static void beforeAll() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeQuery(createUserTableQuery);
        statement.close();
    }

    @AfterAll
    public static void afterAll() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeQuery(dropUserTableQuery);
        statement.close();
    }

    @AfterEach
    public void after() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeQuery(deleteFromUserQuery);
        statement.close();
    }

    @Test
    public void createUser() {
    }

    @Test
    public void getUserByLogin() throws SQLException {

        String login = "foo2";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(insertUsersQuery);
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = User.builder()
                    .id(rs.getInt("id"))
                    .login(rs.getString("login"))
                    .password(rs.getString("password"))
                    .build();
            users.add(user);
        }
        statement.close();
        User foo2 = users.stream().filter(user -> login.equals(user.getLogin())).findAny().orElse(null);
        assertEquals(foo2, userRepository.getUserByLogin(login));

    }

    @Test
    public void updateUser() {
    }

    @Test
    public void deleteUserById() {
    }

}