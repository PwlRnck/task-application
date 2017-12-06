package com.crud.tasks.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@Component
@Getter
/*@PropertySources({
        @PropertySource("file:C:\\Users\\PR\\IdeaProjects\\tasks\\src\\main\\resources\\application.properties"),
        @PropertySource("file:C:\\Users\\PR\\IdeaProjects\\tasks\\src\\main\\resources\\secret.properties")//("classpath:secret.properties")
})*/
public class AdminConfig {
    @Value("${admin.mail}")
    private String adminMail;

    @Value("${admin.name}")
    private String adminName;

    @Value("${info.company.name}")
    private String companyName;

    @Value("${info.company.email}")
    private String companyEmail;

    @Value("${info.company.phone}")
    private String companyPhone;

}
