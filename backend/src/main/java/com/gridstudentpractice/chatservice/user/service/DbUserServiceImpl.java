package com.gridstudentpractice.chatservice.user.service;

import com.gridstudentpractice.chatservice.DbUtil;
import com.gridstudentpractice.chatservice.message.model.Message;
import com.gridstudentpractice.chatservice.user.model.User;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class DbUserServiceImpl implements UserService {

    final static String addUserSql = "INSERT INTO users (login, password) VALUES (\'?\', \'?\')" ;
    final static String checkUserSql = "SELECT EXISTS(SELECT login FROM user WHERE login = ";

    @Override
    public User addUser(User user) {

        String uLogin = user.getULogin();
        String uPass = user.getUPass();

        try (PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(addUserSql)) {

            preparedStatement.setString(1, uLogin);
            preparedStatement.setString(2, uPass);

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public boolean checkUser(User user) {

        String uLogin = user.getULogin();
        boolean result = false;

        try (Statement statement = DbUtil.getConnection().createStatement()) {
            try (ResultSet rs = statement.executeQuery(checkUserSql)) {
                List<Message> messages = new ArrayList<>();

                while (rs.next()) {

                    String sender = rs.getString("sender");
                    String body = rs.getString("body");
                    String time1 = rs.getString("time1");

                    statement.close();
                }

                return result;

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
