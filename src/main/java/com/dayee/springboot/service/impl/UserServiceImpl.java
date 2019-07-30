package com.dayee.springboot.service.impl;

import com.dayee.springboot.PO.User;
import com.dayee.springboot.mapper.UserMapper;
import com.dayee.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int add(User user) {
        userMapper.insert(user);
        int id = user.getId();
        return id;
    }
}
