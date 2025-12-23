package com.spring.service;

import com.spring.model.User;

public interface UserService {
    User getUser();
    void saveUser(User user);
}