package com.lk.blog.mapper;

import com.lk.blog.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    public void addUser(User user);

    public User getUserById(String id);

    public void deleteUserById(String id);

    public User getUserByEmail(String email);
}
