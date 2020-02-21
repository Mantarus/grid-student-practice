package com.gridstudentpractice.chatservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    private static Connection connection = null;

    public static Connection getConnection(AppProperties appProperties) {

        if (connection == null) {

            try {
                connection = DriverManager.getConnection(appProperties.getDatabaseUrl(), appProperties.getDatabaseUser(), appProperties.getDatabasePassword());

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Connection Failed");
            }

            if (connection != null) {
                System.out.println("You successfully connected to database now");
            } else {
                System.out.println("Failed to make connection to database");
            }

            return connection;

        } else
            return connection;
    }
}
