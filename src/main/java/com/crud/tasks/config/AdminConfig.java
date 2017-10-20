package com.crud.tasks.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Getter
@PropertySource("file:C:\\Users\\PR\\IdeaProjects\\tasks\\src\\main\\resources\\secret.properties")//("classpath:secret.properties")
public class AdminConfig {
    @Value("${admin.mail}")
    private String adminMail;
}
