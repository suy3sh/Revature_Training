package org.example;

import org.example.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example") // This scans for @Component, @Service, etc
public class AppConfig {

    // Bean definitions for things that are not components

    @Bean
    public User defaultUser(){
        return new User("Jane Smith", "jane@example.com");
    }

    @Bean(name = "adminUser")
    public User adminUser(){
        User user = new User();
        user.setUsername("Admin");
        user.setEmail("admin@example.com");
        return user;
    }
}
