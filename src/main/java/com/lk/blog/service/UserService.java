package com.lk.blog.service;

import com.lk.blog.exception.ParameterException;
import com.lk.blog.model.User;

public interface UserService {
    public User getUserById(String id) throws ParameterException;

    public void saveUser(User user);
}
