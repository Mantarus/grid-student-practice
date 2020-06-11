package com.gridstudentpractice.chatservice.repository;

import com.gridstudentpractice.chatservice.model.RoleDto;
import com.gridstudentpractice.chatservice.model.UserDto;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ActiveProfiles({"test","orm"})
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserRepositoryImplTest {

    @Autowired
    private Connection connection;

    @Autowired
    private UserRepository userRepository;

    private static final String insertRolesQuery = "INSERT INTO roles VALUES (1, 'foo_role1'), (2, 'foo_role2');";
    private static final String insertUsersQuery = "INSERT INTO users VALUES (1, 'foo1', 'pass1'), " +
                                                                            "(2, 'foo2', 'pass2'), " +
                                                                            "(3, 'foo3', 'pass3');";
    private static final String insertUserRolesQuery = "INSERT INTO user_role (user_id, role_id) VALUES (1, 1), (1, 2), (2, 1), (3, 1);";
    private static final String selectUsersQuery = "SELECT * FROM users;";
    private static final String selectRolesQuery = "SELECT * FROM roles;";
    private static final String selectUserRolesQuery = "SELECT * FROM user_role;";
    private static final String clearUserRolesQuery = "DELETE FROM user_role CASCADE;";

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
        statement.close();
    }

    private UserDto findUserById (int id, List<UserDto> userDtos) {
        for (UserDto userDto : userDtos) {
            if (userDto.getId() == id) {
                return userDto;
            }
        }
        return null;
    }

    @Test
    public void getUserByLogin() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(insertRolesQuery);
        statement.executeUpdate(insertUsersQuery);

        String userLogin = "foo3";
        UserDto repoUserDto = userRepository.getUserByLogin(userLogin);

        ResultSet rs1 = statement.executeQuery(selectRolesQuery);
        List<RoleDto> roleDtos = new ArrayList<>();
        while (rs1.next()) {
            RoleDto roleDto = RoleDto.builder()
                    .id(rs1.getInt("id"))
                    .name(rs1.getString("name"))
                    .build();
            roleDtos.add(roleDto);
        }

        ResultSet rs2 = statement.executeQuery(selectUsersQuery);
        List<UserDto> userDtos = new ArrayList<>();
        while (rs2.next()) {
            UserDto userDto = UserDto.builder()
                    .id(rs2.getInt("id"))
                    .login(rs2.getString("login"))
                    .password(rs2.getString("password"))
                    .build();
            userDtos.add(userDto);
        }
        statement.close();

        for (UserDto userDto : userDtos) {
            if (userDto.getLogin().equals(userLogin)) {
                assertEquals(userDto.getId(), repoUserDto.getId());
                assertEquals(userDto.getLogin(), repoUserDto.getLogin());
                assertEquals(userDto.getPassword(), repoUserDto.getPassword());
            }
        }
    }

    @Test
    public void CreateUser() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(insertRolesQuery);
        UserDto foo1 = UserDto.builder()
                .id(1)
                .login("foo1")
                .password("pass1")
                .build();

        userRepository.createUser(foo1);

        ResultSet rs = statement.executeQuery(selectUsersQuery);
        List<UserDto> userDtos = new ArrayList<>();
        while (rs.next()) {
           UserDto userDto = UserDto.builder()
                   .id(rs.getInt("id"))
                   .login(rs.getString("login"))
                   .password(rs.getString("password"))
                   .build();
           userDtos.add(userDto);
        }
        statement.close();
        assertEquals(foo1.getId(), findUserById(foo1.getId(), userDtos).getId());
        assertEquals(foo1.getLogin(), findUserById(foo1.getId(), userDtos).getLogin());
        assertEquals(foo1.getPassword(), findUserById(foo1.getId(), userDtos).getPassword());
    }

    @Test
    public void updateUser() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(insertRolesQuery);
        statement.executeUpdate(insertUsersQuery);

        UserDto foo1 = UserDto.builder()
                .id(1)
                .login("foo01")
                .password("pass01")
                .build();
        userRepository.updateUserLoginAndPassword(foo1);

        ResultSet rs = statement.executeQuery(selectUsersQuery);
        List<UserDto> userDtos = new ArrayList<>();
        while (rs.next()) {
            UserDto userDto = UserDto.builder()
                    .id(rs.getInt("id"))
                    .login(rs.getString("login"))
                    .password(rs.getString("password"))
                    .build();
            userDtos.add(userDto);
        }
        statement.close();

        assertEquals(foo1.getId(), findUserById(foo1.getId(), userDtos).getId());
        assertEquals(foo1.getLogin(), findUserById(foo1.getId(), userDtos).getLogin());
        assertEquals(foo1.getPassword(), findUserById(foo1.getId(), userDtos).getPassword());
    }

    @Test
    public void deleteUserById() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(insertRolesQuery);
        statement.executeUpdate(insertUsersQuery);

        int userId = 1;
        userRepository.deleteUserById(userId);

        ResultSet rs = statement.executeQuery(selectUsersQuery);
        List<UserDto> userDtos = new ArrayList<>();
        while (rs.next()) {
            UserDto userDto = UserDto.builder()
                    .id(rs.getInt("id"))
                    .login(rs.getString("login"))
                    .password(rs.getString("password"))
                    .build();
            userDtos.add(userDto);
        }
        statement.close();

        assertNull(findUserById(userId, userDtos));
    }

    @Test
    public void addRoleToUser() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(insertUsersQuery);
        statement.executeUpdate(insertRolesQuery);
        int roleId = 1, userId = 2;
        userRepository.addRoleToUser(roleId, userId);
        ResultSet rs = statement.executeQuery(selectUserRolesQuery);
        if (rs.next()) {
            assertEquals(rs.getInt("user_id"), userId);
            assertEquals(rs.getInt("role_id"), roleId);
        }
        statement.executeUpdate(clearUserRolesQuery);
        statement.close();
    }

    @Test
    public void getUserRoles() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(insertRolesQuery);
        statement.executeUpdate(insertUsersQuery);
        statement.executeUpdate(insertUserRolesQuery);
        int userId = 1;

        List<UserDto> testUserDtos = new ArrayList<>();
        ResultSet rs = statement.executeQuery(selectUsersQuery);
        while (rs.next()) {
            UserDto testUserDto = UserDto.builder()
                    .id(rs.getInt("id"))
                    .login(rs.getString("login"))
                    .password(rs.getString("password"))
                    .build();
            testUserDtos.add(testUserDto);
        }
        List<RoleDto> testRoleDtos = new ArrayList<>();
        ResultSet rs1 = statement.executeQuery(selectRolesQuery);
        while (rs1.next()) {
            RoleDto testRoleDto = RoleDto.builder()
                    .id(rs1.getInt("id"))
                    .name(rs1.getString("name"))
                    .build();
            testRoleDtos.add(testRoleDto);
        }
        List<RoleDto> repoRoleDtos = userRepository.getUserRoles(findUserById(userId, testUserDtos).getLogin());

        for (int i = 0; i < testRoleDtos.size(); i++) {
            if (repoRoleDtos.get(i).getId() == testRoleDtos.get(i).getId()) {
                assertEquals(repoRoleDtos.get(i).getName(), testRoleDtos.get(i).getName());
            }
        }
    }
}
