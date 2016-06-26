package com.lk.blog.service;

import com.lk.blog.model.User;

public interface UserService {
    public User getUserById(String id);

    public User saveUser(User user);

    public User getUserByEmail(String email);
}
