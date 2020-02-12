package com.gridstudentpractice.chatservice.message;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Util {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/chat";
    static final String USER = "postgres";
    static final String PASS = "password";
    private static Connection connection = null;

    public static Connection getConnection() {

        if (connection == null) {

            try {
                connection = DriverManager.getConnection(DB_URL, USER, PASS);

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

    public static String getDB_URL() {
        return DB_URL;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getPASS() {
        return PASS;
    }
}
