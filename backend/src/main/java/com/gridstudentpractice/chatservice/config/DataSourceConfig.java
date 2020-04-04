package com.gridstudentpractice.chatservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Autowired
    private DbProperties dbProperties;

    @Profile("dev")
    @Bean
    @Primary
    public DataSourceProperties getProperties() {
        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        dataSourceProperties.setUrl(dbProperties.getUrl());
        dataSourceProperties.setUsername(dbProperties.getUsername());
        dataSourceProperties.setPassword(dbProperties.getPassword());
        return dataSourceProperties;
    }

    @Profile("dev")
    @Bean
    public DataSource getDatasource() {
        return getProperties().initializeDataSourceBuilder().build();
    }

}
