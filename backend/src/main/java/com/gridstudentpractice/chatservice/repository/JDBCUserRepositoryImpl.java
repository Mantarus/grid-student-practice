package com.gridstudentpractice.chatservice.repository;

import com.gridstudentpractice.chatservice.DbUtil;
import com.gridstudentpractice.chatservice.exception.RepositoryException;
import com.gridstudentpractice.chatservice.model.User;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JDBCUserRepositoryImpl implements UserRepository {

    final static private String addUserSql = "INSERT INTO users (login, password) VALUES (?, ?)";
    final static private String checkUserSql = "SELECT * FROM users u WHERE u.login = ? ORDER BY u.id";
    final static private String updateUserQuery = "UPDATE users u SET login = ?, password = ? WHERE u.id = ?";
    final static private String deleteUserQuery = "DELETE FROM users u WHERE u.id = ?";

    @Override
    public void createUser(User user) {
        try (PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(addUserSql)) {

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException("User creation error", e);
        }
    }

    @Override
    public User getUser(String userLogin) {
        try (PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(checkUserSql)) {

            preparedStatement.setString(1, userLogin);

            try (ResultSet rs = preparedStatement.executeQuery()) {

                User user = new User();

                while (rs.next()) {

                    user.setId(rs.getInt("id"));
                    user.setLogin(rs.getString("login"));
                    user.setPassword(rs.getString("password"));
                }

                if (!(user.getLogin() == null || user.getPassword() == null))
                    return user;
                else throw new RepositoryException("User login or password can't be null");

            } catch (SQLException e) {
                throw new RepositoryException("ResultSet error", e);
            } finally {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            throw new RepositoryException("User reading error", e);
        }
    }

    @Override
    public void updateUser(User user, int id) {
        try (PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(updateUserQuery)) {

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException("User update error", e);
        }
    }

    @Override
    public void deleteUser(int id) {
        try (PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(deleteUserQuery)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException("User delete error", e);
        }
    }
}
