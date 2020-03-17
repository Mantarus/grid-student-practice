package com.gridstudentpractice.chatservice.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties(prefix = "postgres")
@Getter
@Profile("dev")
public class PostgresProperties {

    @Value(("${postgres.url}"))
    private String url;

    @Value(("${postgres.username}"))
    private String username;

    @Value(("${postgres.password}"))
    private String password;

}
