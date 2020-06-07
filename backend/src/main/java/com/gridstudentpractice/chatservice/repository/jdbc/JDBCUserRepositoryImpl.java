package com.gridstudentpractice.chatservice.repository.jdbc;

import com.gridstudentpractice.chatservice.exception.RepositoryException;
import com.gridstudentpractice.chatservice.model.UserDto;
import com.gridstudentpractice.chatservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Profile("jdbc")
@Repository
public class JDBCUserRepositoryImpl implements UserRepository {

    @Autowired
    private Connection connection;

    final static private String addUserSql = "INSERT INTO users (login, password, role) VALUES (?, ?, ?::integer)";
    final static private String checkUserSql = "SELECT u.id, u.login, u.password, r.name AS role FROM users u " +
                                                "JOIN roles r on u.role = r.id " +
                                                "WHERE u.login = ? ORDER BY u.id";
    final static private String updateUserLoginAndPasswordSql = "UPDATE users u SET login = ?, password = ? WHERE u.id = ?";
    final static private String updateUserRoleSql = "UPDATE users u SET role = ? WHERE u.id = ?";
    final static private String deleteUserSql = "DELETE FROM users u WHERE u.id = ?";

    @Override
    public void createUser(UserDto userDto) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(addUserSql)) {

            preparedStatement.setString(1, userDto.getLogin());
            preparedStatement.setString(2, userDto.getPassword());
            preparedStatement.setString(3, userDto.getRole());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException("UserDto creation error", e);
        }
    }

    @Override
    public UserDto getUserByLogin(String userLogin) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                checkUserSql,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY
        )) {

            preparedStatement.setString(1, userLogin);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return UserDto.builder()
                            .id(rs.getInt("id"))
                            .login(rs.getString("login"))
                            .password(rs.getString("password"))
                            .role(rs.getString("role"))
                            .build();
                } else throw new RepositoryException("No such user") ;

            } catch (SQLException e) {
                throw new RepositoryException("ResultSet error", e);
            }
        } catch (SQLException e) {
            throw new RepositoryException("UserDto reading error", e);
        }
    }

    @Override
    public void updateUserLoginAndPassword(UserDto userDto) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateUserLoginAndPasswordSql)) {

            preparedStatement.setString(1, userDto.getLogin());
            preparedStatement.setString(2, userDto.getPassword());
            preparedStatement.setInt(3, userDto.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException("UserDto login and password update error", e);
        }
    }

    @Override
    public void updateUserRole(UserDto userDto) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateUserRoleSql)) {

            preparedStatement.setString(1, userDto.getRole());
            preparedStatement.setInt(2, userDto.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException("UserDto role update error", e);
        }
    }

    @Override
    public void deleteUserById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteUserSql)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException("UserDto delete error", e);
        }
    }
}
