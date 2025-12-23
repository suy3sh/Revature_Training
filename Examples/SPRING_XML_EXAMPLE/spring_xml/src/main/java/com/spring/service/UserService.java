package com.spring.service;
import com.spring.models.User;

public interface UserService {
    User getUser();
    void saveUser(User user);
}
