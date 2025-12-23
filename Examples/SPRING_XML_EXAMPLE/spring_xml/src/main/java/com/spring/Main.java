package com.spring;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.models.User;
import com.spring.service.UserService;

public class Main {
    public static void main(String[] args) {
        //Load Spring context from XML configuration
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

         // Get bean by ID
        UserService userService = (UserService) context.getBean("userService");

        // use the bean
        User user = userService.getUser();
        System.out.println("Default user from service: " + user);

        // Get the other bean
        User defaultUser = context.getBean("defaultUser", User.class);
        System.out.println("Default user bean: " + defaultUser);

        User adminUser = context.getBean("adminUser", User.class);
        System.out.println("Admin User: " + adminUser);

        // Get prototype bean (new instance each time)
        User prototype1 = context.getBean("prototypeUser", User.class);
        User prototype2 = context.getBean("prototypeUser", User.class);

        System.out.println("Are they the same? " + (prototype1 == prototype2));

         // Get non-bean configuration
        Map<String, String> appConfig = context.getBean("appConfig", Map.class);
        System.out.println("App Config: " + appConfig );

        // Get bean by type
        UserService serviceByType = context.getBean(UserService.class);
        System.out.println("Service by type: " + serviceByType);

        // Close the context
        ((ClassPathXmlApplicationContext) context).close();

    }
}