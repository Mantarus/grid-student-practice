package com.gridstudentpractice.chatservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Autowired
    private AppProperties appProperties;

    @Bean
    @Primary
    public DataSourceProperties getProperties() {
        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        dataSourceProperties.setUrl(appProperties.getDatabaseUrl());
        dataSourceProperties.setUsername(appProperties.getDatabaseUsername());
        dataSourceProperties.setPassword(appProperties.getDatabasePassword());
        return dataSourceProperties;
    }

    @Bean
    public DataSource getDatasource() {
        return getProperties().initializeDataSourceBuilder().build();
    }

}
