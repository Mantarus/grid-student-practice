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

    final static String addUserSql = "INSERT INTO users (login, password) VALUES (?, ?)" ;
    final static String checkUserSql = "SELECT * FROM users WHERE login = ?";

    private User user = new User();

    @Override
    public boolean createUser(User user) {
        try (PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(addUserSql)) {

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());

            preparedStatement.executeUpdate();
            if (preparedStatement.executeUpdate() == 0) {
                return false;
            }
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RepositoryException("User creation error", e);
        }
        return true;
    }

    @Override
    public User readUser(String loginToGet) {
        try (PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(checkUserSql)) {

            preparedStatement.setString(1, loginToGet);

            try (ResultSet rs = preparedStatement.executeQuery()) {

                while (rs.next()) {

                    user.setId(rs.getInt("id"));
                    user.setLogin(rs.getString("login"));
                    user.setPass(rs.getString("password"));
                }

                preparedStatement.close();

                if (!(user.getLogin().equals("null") || user.getPassword().equals("null")))
                    return user;
                else return null;

            } catch (SQLException e) {
                throw new RepositoryException("ResultSet error", e);
            }
        } catch (SQLException e) {
            throw new RepositoryException("User reading error", e);
        }
    }
}
