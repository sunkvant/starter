package com.itbootcamp.starter.webapp.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.MultipartConfigElement;
import java.net.URI;
import java.net.URISyntaxException;


@ComponentScan({"com.itbootcamp.starter.webapp.controller","com.itbootcamp.starter.services","com.itbootcamp.starter.webapp.dto","com.itbootcamp.starter.security"})
@EnableJpaRepositories("com.itbootcamp.starter.repository")
@EntityScan("com.itbootcamp.starter.datamodel")
@SpringBootApplication
public class Starter extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Starter.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Starter.class, args);

    }

    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() throws URISyntaxException {


        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//heroku environment
/*        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        dataSource.setUrl(dbUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);*/

//localhost environment----------------------------
        dataSource.setDriverClassName("org.postgresql.Driver");


        dataSource.setUrl("jdbc:postgresql://localhost:5432/starter");
        dataSource.setUsername("postgres");
        dataSource.setPassword("admin");

        return dataSource;
    }


    @Bean(name = "commonsMultipartResolver")
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("10MB");
        factory.setMaxRequestSize("10MB");
        return factory.createMultipartConfig();
    }

}