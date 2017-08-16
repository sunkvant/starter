package com.itbootcamp.starter.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created by foooox on 14.8.17.
 */@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {


/*    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        *//*super.addViewControllers(registry);*//**//*
        registry.addViewController("/login").setViewName("login");
 *//**//**//**//*       registry.addViewController("/logout").setViewName("logout");*//**//**//**//*
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("index");*//*

    }*/

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer
    propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}

