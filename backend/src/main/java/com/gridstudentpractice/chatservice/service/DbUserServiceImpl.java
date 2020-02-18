package com.gridstudentpractice.chatservice.service;

import com.gridstudentpractice.chatservice.DbUtil;
import com.gridstudentpractice.chatservice.model.User;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class DbUserServiceImpl implements UserService {

    final static String addUserSql = "INSERT INTO users (login, password) VALUES (?, ?)" ;
    final static String checkUserSql = "SELECT login FROM users WHERE login = ?";

    @Override
    public User addUser(User user) {

        String uLogin = user.getULogin();
        String uPass = user.getUPass();

        try (PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(addUserSql)) {

            preparedStatement.setString(1, uLogin);
            preparedStatement.setString(2, uPass);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkUser(User user) {

        String uLogin = user.getULogin();
        boolean result = false;

        try (PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(checkUserSql)) {

            preparedStatement.setString(1, uLogin);

            try (ResultSet rs = preparedStatement.executeQuery()) {

                while (rs.next()) {

                    String login = rs.getString("login");

                    if (login.equals(uLogin))
                        result = true;
                }

                preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return result;
    }
}
