package com.itbootcamp.starter.webapp.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"com.itbootcamp.starter.webapp.controller","com.itbootcamp.starter.services"})
@EnableJpaRepositories("com.itbootcamp.starter.repository")
@EntityScan("com.itbootcamp.starter.datamodel")
@SpringBootApplication
@EnableAutoConfiguration
public class Starter extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Starter.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Starter.class, args);
    }

}