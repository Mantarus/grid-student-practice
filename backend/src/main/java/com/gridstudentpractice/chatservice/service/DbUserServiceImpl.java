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
    final static String checkUserSql = "SELECT * FROM users WHERE login = ?";

    @Override
    public boolean addUser(User user) {

        try (PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(addUserSql)) {

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());

            if (preparedStatement.executeUpdate() == 0) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public User getUser(String loginToGet) {

        try (PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(checkUserSql)) {

            preparedStatement.setString(1, loginToGet);

            try (ResultSet rs = preparedStatement.executeQuery()) {

                User user = new User();

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
                e.printStackTrace();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }
}
