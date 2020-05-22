package com.gridstudentpractice.chatservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Profile("test")
@Configuration
public class H2Config {

    @Autowired
    private DbProperties dbProperties;

    private DataSourceProperties getProperties() {
        DataSourceProperties properties = new DataSourceProperties();
        properties.setUrl(dbProperties.getUrl());
        return properties;
    }

    @Bean
    public DataSource getDataSource() {
        return getProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public Connection getConnection() {
        Connection connection = null;

        try {
            connection = getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Failed");
        }

        if (connection != null) {
            System.out.println("You successfully connected to test database");
        } else {
            System.out.println("Failed to make connection to database");
        }

        return connection;
    }
}
