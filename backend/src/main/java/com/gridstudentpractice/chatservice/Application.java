package com.gridstudentpractice.chatservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class Application {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/chat";
    static final String USER = "postgres";
    static final String PASS = "password";
    private static Connection connection = null;

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            return ;
        }

        System.out.println("PostgreSQL JDBC Driver successfully connected");

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Failed");
            return ;
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
