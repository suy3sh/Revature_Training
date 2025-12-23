package org.example.service;

import org.example.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component // This replaces <bean id=userService" class="...">
public class UserServiceImpl implements UserService{

    @Value("John Doe") // This replaces <property name="defaultUsername" value="John Doe"/>
    private String defaultUsername;

    @Value("john@example.com")
    private String defaultEmail;

    @Override
    public User getUser() {
        return new User(defaultUsername, defaultEmail);
    }

    @Override
    public void saveUser(User user) {
        System.out.println("Saving User: " + user);
    }
}