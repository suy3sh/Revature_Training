package com.spring;

import com.spring.model.User;
import com.spring.service.UserService;
import com.spring.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class AppConfig {


    // equivalent to <bean> in XML
    @Bean
    public UserService userService() {
        UserServiceImpl userService = new UserServiceImpl();

        // property injection
        userService.setDefaultUsername("defaultUser");
        userService.setDefaultEmail("default@example.com");

        return userService;
    }

    @Bean
    public User defaultUser() {
        return new User("JaneSmith", "jane@example.com");
    }

    @Bean
    @Scope("prototype")
    public User prototypeUser(){
        return new User("Prototype User", "prototype@example.com");
    }
        


}
