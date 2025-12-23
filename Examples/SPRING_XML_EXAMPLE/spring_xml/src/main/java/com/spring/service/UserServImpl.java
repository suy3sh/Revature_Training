package com.spring.service;

import com.spring.models.User;

public class UserServImpl implements UserService {

    private String defaultName;
    private String defaultEmail;

    @Override
    public User getUser() {
        // For demonstration, returning a dummy user
        return new User(defaultName, defaultEmail);
    }

    @Override
    public void saveUser(User user) {
        // Implementation for saving a user
        System.out.println("User saved: " + user);
    }

    //Setters for property Injection
    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }
    
    public void setDefaultEmail(String defaultEmail) {
        this.defaultEmail = defaultEmail;
    }
}