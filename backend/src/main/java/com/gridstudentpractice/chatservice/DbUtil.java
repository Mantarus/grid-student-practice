package com.gridstudentpractice.chatservice;

import com.gridstudentpractice.chatservice.config.H2Properties;
import com.gridstudentpractice.chatservice.config.PostgresProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    private static Connection postgresConnection = null;
    private static Connection h2Connection = null;

    @Autowired
    private PostgresProperties postgresProperties;
    @Autowired
    private H2Properties h2Properties;

    @Bean
    @Profile("dev")
    public Connection getPostgresConnection() {

        if (postgresConnection == null) {

            try {
                postgresConnection = DriverManager.getConnection(postgresProperties.getUrl(),
                        postgresProperties.getUsername(), postgresProperties.getPassword());

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Connection Failed");
            }

            if (postgresConnection != null) {
                System.out.println("You successfully connected to database now");
            } else {
                System.out.println("Failed to make connection to database");
            }

        }
        return postgresConnection;
    }

    @Bean
    @Profile("test")
    public Connection getH2Connection() {

        if (h2Connection == null) {

            try {
                h2Connection = DriverManager.getConnection(h2Properties.getUrl());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (h2Connection != null) {
                System.out.println("You successfully connected to test database");
            } else {
                System.out.println("Failed to make connection to test database");
            }
        }

        return h2Connection;
    }
    
}
