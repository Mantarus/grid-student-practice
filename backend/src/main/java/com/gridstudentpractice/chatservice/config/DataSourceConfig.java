package com.gridstudentpractice.chatservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Profile("orm")
@Configuration
public class DataSourceConfig {

    @Autowired
    private DbProperties dbProperties;

    @Profile("dev")
    private DataSourceProperties getPostgresProperties() {
        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        dataSourceProperties.setUrl(dbProperties.getUrl());
        dataSourceProperties.setUsername(dbProperties.getUsername());
        dataSourceProperties.setPassword(dbProperties.getPassword());
        return dataSourceProperties;
    }

    @Profile("dev")
    @Bean
    public DataSource getPostgresDatasource() {
        return getPostgresProperties().initializeDataSourceBuilder().build();
    }

    @Profile("test")
    private DataSourceProperties getH2Properties() {
        DataSourceProperties properties = new DataSourceProperties();
        properties.setUrl(dbProperties.getUrl());
        return properties;
    }

    @Profile("test")
    @Bean
    public DataSource getH2DataSource() {
        return getH2Properties().initializeDataSourceBuilder().build();
    }

    @Profile("test")
    @Bean
    public Connection getH2Connection() {
        Connection connection = null;
        try {
            connection = getH2DataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
