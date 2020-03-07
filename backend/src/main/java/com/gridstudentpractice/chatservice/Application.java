package com.gridstudentpractice.chatservice;

import com.gridstudentpractice.chatservice.config.SwaggerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application {

    @Autowired
    private SwaggerConfiguration swaggerConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Docket setSwaggerConfiguration() {
        return swaggerConfiguration.setSwaggerConfiguration();
    }

}
