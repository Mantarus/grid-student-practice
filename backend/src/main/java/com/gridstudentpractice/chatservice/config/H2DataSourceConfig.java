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
public class H2DataSourceConfig {

    @Autowired
    private DbProperties dbProperties;

    @Profile("orm")
    private DataSourceProperties getH2Properties() {
        DataSourceProperties properties = new DataSourceProperties();
        properties.setUrl(dbProperties.getUrl());
        return properties;
    }

    @Profile("orm")
    @Bean
    public DataSource getH2DataSource() {
        return getH2Properties().initializeDataSourceBuilder().build();
    }

    @Profile("orm")
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
