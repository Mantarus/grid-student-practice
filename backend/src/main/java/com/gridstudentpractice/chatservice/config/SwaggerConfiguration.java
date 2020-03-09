package com.gridstudentpractice.chatservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket setSwaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/**"))
                .apis(RequestHandlerSelectors.basePackage("com.gridstudentpractice.chatservice"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Chatservice API",
                "API for Grid Dynamics student practice",
                "1.0",
                "Free to use",
                new Contact("Mantarus", "https://github.com/Mantarus", "https://github.com/Mantarus"),
                "API License",
                "https://github.com/Mantarus/grid-student-practice",
                Collections.emptyList());
    }

}
