package com.crud.tasks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CommonPropertiesConfiguration extends PropertiesConfiguration {
    @Bean
    @Profile(Profiles.LOCAL)
    public static PropertySourcesPlaceholderConfigurer localProperties() {
        return createPropertySourcesPlaceholderConfigurer(
                "application.properties");
    }

    @Bean
    @Profile(Profiles.HEROKU)
    public static PropertySourcesPlaceholderConfigurer herokuProperties() {
        return createPropertySourcesPlaceholderConfigurer(
                "application-heroku.properties");
    }
}