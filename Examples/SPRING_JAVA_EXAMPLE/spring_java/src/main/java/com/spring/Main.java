package com.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.model.User;
import com.spring.service.UserService;

public class Main {
    public static void main(String[] args) {
        // Load Spring context from java configuration
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Get bean by type (Recommended in Java Config)
        UserService userService = context.getBean(UserService.class);

        // Use the bean
        User user = userService.getUser();
        System.out.println("Default user from service: " + user);

        // default user
        User defaultUser = context.getBean("defaultUser", User.class);
        System.out.println(defaultUser);

        // prototypes
        User p1 = context.getBean("prototypeUser", User.class);
        User p2 = context.getBean("prototypeUser", User.class);
        System.out.println(p1 == p2);

        //close
        ((AnnotationConfigApplicationContext) context).close();
    }
}