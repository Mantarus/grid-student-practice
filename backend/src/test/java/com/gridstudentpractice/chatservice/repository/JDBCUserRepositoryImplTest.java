package com.gridstudentpractice.chatservice.repository;

import com.gridstudentpractice.chatservice.model.User;
import org.bitbucket.radistao.test.annotation.AfterAllMethods;
import org.bitbucket.radistao.test.annotation.BeforeAllMethods;
import org.bitbucket.radistao.test.runner.BeforeAfterSpringTestRunner;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.Assert.*;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RunWith(BeforeAfterSpringTestRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class JDBCUserRepositoryImplTest {

    @Autowired
    private Connection connection;

    @Autowired
    private UserRepository userRepository;

    private static final String createUserTableQuery = "CREATE TABLE users " +
                                                            "( " +
                                                            "id INT NOT NULL AUTO_INCREMENT, " +
                                                            "login TEXT NOT NULL, " +
                                                            "password TEXT NOT NULL," +
                                                            "PRIMARY KEY (id) " +
                                                            ");";
    private static final String dropUserTableQuery = "DROP TABLE users;";
    private static final String clearUserTableQuery = "DELETE FROM users; " +
                                                        "ALTER TABLE users ALTER COLUMN id RESTART WITH 1;";
    private static final String insertUsersQuery = "INSERT INTO users VALUES (1, 'foo1', 'pass1'), " +
                                                                                "(2, 'foo2', 'pass2'), " +
                                                                                "(3, 'foo3', 'pass3');";
    private static final String selectUsersQuery = "SELECT * FROM users;";

    @BeforeAllMethods
    public void beforeAll() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(createUserTableQuery);
        statement.close();
    }

    @After
    public void after() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(clearUserTableQuery);
        statement.close();
    }

    @AfterAllMethods
    public void afterAll() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(dropUserTableQuery);
        statement.close();
    }

    private User findUserById (int id, List<User> users) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Test
    public void getUserByLogin() throws SQLException {
        Statement statement1 = connection.createStatement();
        statement1.executeUpdate(insertUsersQuery);
        statement1.close();

        String userLogin = "foo3";
        User repoUser = userRepository.getUserByLogin(userLogin);

        Statement statement2 = connection.createStatement();
        ResultSet rs = statement2.executeQuery(selectUsersQuery);
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = User.builder()
                    .id(rs.getInt("id"))
                    .login(rs.getString("login"))
                    .password(rs.getString("password"))
                    .build();
            users.add(user);
        }
        statement2.close();

        for (User user : users) {
            if (user.getLogin().equals(userLogin)) {
                assertEquals(user.getId(), repoUser.getId());
                assertEquals(user.getLogin(), repoUser.getLogin());
                assertEquals(user.getPassword(), repoUser.getPassword());
            }
        }
    }

    @Test
    public void CreateUser() throws SQLException {
        User foo1 = User.builder()
                .id(1)
                .login("foo1")
                .password("pass1")
                .build();

        userRepository.createUser(foo1);

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(selectUsersQuery);
        if (rs.next()) {
            assertEquals(foo1.getId(), rs.getInt("id"));
            assertEquals(foo1.getLogin(), rs.getString("login"));
            assertEquals(foo1.getPassword(), rs.getString("password"));
        }
        statement.close();
    }

    @Test
    public void updateUser() throws SQLException {
        Statement statement1 = connection.createStatement();
        statement1.executeUpdate(insertUsersQuery);
        statement1.close();

        User foo1 = User.builder()
                .id(1)
                .login("foo01")
                .password("pass01")
                .build();
        userRepository.updateUser(foo1);

        Statement statement2 = connection.createStatement();
        ResultSet rs = statement2.executeQuery(selectUsersQuery);
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = User.builder()
                    .id(rs.getInt("id"))
                    .login(rs.getString("login"))
                    .password(rs.getString("password"))
                    .build();
            users.add(user);
        }
        statement2.close();

        assertEquals(foo1.getId(), findUserById(foo1.getId(), users).getId());
        assertEquals(foo1.getLogin(), findUserById(foo1.getId(), users).getLogin());
        assertEquals(foo1.getPassword(), findUserById(foo1.getId(), users).getPassword());
    }

    @Test
    public void deleteUserById() throws SQLException {
        Statement statement1 = connection.createStatement();
        statement1.executeUpdate(insertUsersQuery);
        statement1.close();

        int userId = 2;
        userRepository.deleteUserById(userId);

        Statement statement2 = connection.createStatement();
        ResultSet rs = statement2.executeQuery(selectUsersQuery);
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = User.builder()
                    .id(rs.getInt("id"))
                    .login(rs.getString("login"))
                    .password(rs.getString("password"))
                    .build();
            users.add(user);
        }
        statement2.close();

        assertNull(findUserById(userId, users));
    }

}
