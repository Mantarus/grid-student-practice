package com.gridstudentpractice.chatservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class AppProperties {

    @Value(("${database.url}"))
    private String databaseUrl;

    @Value(("${database.username}"))
    private String databaseUsername;

    @Value(("${database.password}"))
    private String databasePassword;

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public String getDatabaseUser() {
        return databaseUsername;
    }
}
