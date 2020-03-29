package com.gridstudentpractice.chatservice.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "database")
@Getter
public class AppProperties {

    @Value(("${database.jdbc-url}"))
    private String databaseUrl;

    @Value(("${database.username}"))
    private String databaseUsername;

    @Value(("${database.password}"))
    private String databasePassword;

}
