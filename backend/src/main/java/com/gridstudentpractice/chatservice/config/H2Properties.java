package com.gridstudentpractice.chatservice.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties(prefix = "h2")
@Getter
@Profile("test")
public class H2Properties implements DbProperties {

    @Value(("${h2.url}"))
    private String url;

    @Value(("${h2.username}"))
    private String username;

    @Value(("${h2.password}"))
    private String password;
}
