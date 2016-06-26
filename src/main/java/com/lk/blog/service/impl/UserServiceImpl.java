package com.lk.blog.service.impl;

import com.lk.blog.exception.ParameterException;
import com.lk.blog.mapper.UserMapper;
import com.lk.blog.model.User;
import com.lk.blog.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final static Logger logger = Logger.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(String userId) throws ParameterException {
        logger.debug("userId = " + userId);
        if (userId == null) {
            throw new ParameterException("");
        }
        return userMapper.getUserById(userId);
    }

    @Override
    public void saveUser(User user) {

    }
}
