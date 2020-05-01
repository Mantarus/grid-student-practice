package com.gridstudentpractice.chatservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Profile("dev")
@Configuration
public class PostgresConfig {

    @Autowired
    private DbProperties dbProperties;

    private DataSourceProperties getProperties() {
        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        dataSourceProperties.setUrl(dbProperties.getUrl());
        dataSourceProperties.setUsername(dbProperties.getUsername());
        dataSourceProperties.setPassword(dbProperties.getPassword());
        return dataSourceProperties;
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
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }

        return connection;
    }
}
