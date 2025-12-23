package com.spring.service;

import com.spring.model.User;

public class UserServiceImpl implements UserService{
    private String defaultUsername;
    private String defaultEmail;

    @Override
    public User getUser() {
        return new User(defaultUsername, defaultEmail);
    }

    @Override
    public void saveUser(User user) {
        System.out.println("Saving user: " + user);
    }

    public void setDefaultUsername(String defaultUsername) {
        this.defaultUsername = defaultUsername;
    }

    public void setDefaultEmail(String defaultEmail) {
        this.defaultEmail = defaultEmail;
    }
}
