package com.gridstudentpractice.chatservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Profile({"dev", "orm"})
@Configuration
public class PostgresDataSourceConfig {

    @Autowired
    private DbProperties dbProperties;

    private DataSourceProperties getPostgresProperties() {
        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        dataSourceProperties.setUrl(dbProperties.getUrl());
        dataSourceProperties.setUsername(dbProperties.getUsername());
        dataSourceProperties.setPassword(dbProperties.getPassword());
        return dataSourceProperties;
    }

    @Bean
    public DataSource getPostgresDatasource() {
        return getPostgresProperties().initializeDataSourceBuilder().build();
    }

}
