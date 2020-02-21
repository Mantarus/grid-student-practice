package com.gridstudentpractice.chatservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class AppProperties {

    @Value(("${database.url}"))
    private String url;

    @Value(("${database.username}"))
    private String username;

    @Value(("${database.password}"))
    private String password;

    public String getDatabaseUrl() {
        System.out.println(url);
        return url;
    }

    public String getDatabasePassword() {
        System.out.println(password);
        return password;
    }

    public String getDatabaseUser() {
        System.out.println(username);
        return username;
    }
}
