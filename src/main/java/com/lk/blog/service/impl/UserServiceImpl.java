package com.lk.blog.service.impl;

import com.lk.blog.exception.BusinessException;
import com.lk.blog.exception.DataErrorException;
import com.lk.blog.exception.ParameterException;
import com.lk.blog.mapper.UserMapper;
import com.lk.blog.model.User;
import com.lk.blog.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private final static Logger logger = Logger.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(String userId) {
        logger.debug("userId = " + userId);
        if (userId == null) {
            throw new ParameterException();
        }
        User user = null;
        try {
            user = userMapper.getUserById(userId);
        } catch (Exception e) {
            throw new DataErrorException(e);
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        if (email == null) {
            throw new ParameterException();
        }
        User user = null;
        try {
            user = userMapper.getUserByEmail(user.getEmail());
        } catch (Exception e) {
            throw new DataErrorException(e);
        }
        return user;
    }

    @Override
    public User saveUser(User user) {
        if (user == null) {
            throw new ParameterException();
        }
        User resultUser = null;
        try {
            userMapper.addUser(user);
            resultUser = userMapper.getUserByEmail(user.getEmail());
        } catch (Exception e) {
            throw new DataErrorException(e);
        }
        if (resultUser == null) {
            throw new BusinessException();
        }
        return resultUser;
    }
}
