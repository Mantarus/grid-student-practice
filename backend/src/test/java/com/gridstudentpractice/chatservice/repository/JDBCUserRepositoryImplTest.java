package com.gridstudentpractice.chatservice.repository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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

    @BeforeAll
    public static void beforeAll() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeQuery(createUserTableQuery);
    }

    @AfterAll
    public static void afterAll() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeQuery(dropUserTableQuery);
    }

    @Test
    public void createUser() {
    }

    @Test
    public void getUserByLogin() {
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void deleteUserById() {
    }

}