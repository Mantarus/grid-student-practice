package com.gridstudentpractice.chatservice;

import com.gridstudentpractice.chatservice.config.DbProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DbUtil {

    private static DbProperties dbProperties;

    @Autowired
    private DbProperties autowiredDbProperties;

    @Bean("dbProperties")
    public void init() {
        dbProperties = autowiredDbProperties;
        System.out.println("bean dbproperties here!");
        System.out.println("DB-url = " + dbProperties.getUrl());
        System.out.println("DB-pass = " + dbProperties.getPassword());
        System.out.println("DB-username = " + dbProperties.getUsername());
    }

    @Bean
    @Profile("dev")
    @DependsOn("dbProperties")
    public static Connection getPostgresConnection() {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(dbProperties.getUrl(),
                   dbProperties.getUsername(), dbProperties.getPassword());

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
    }

    @Bean
    @Profile("test")
    @DependsOn("dbProperties")
    public static Connection getH2Connection() {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(dbProperties.getUrl());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (connection != null) {
            System.out.println("You successfully connected to test database");
        } else {
            System.out.println("Failed to make connection to test database");
        }

        return connection;
    }
    
}
