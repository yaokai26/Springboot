package com.dayee.springboot.service.impl;

import com.dayee.springboot.PO.User;
import com.dayee.springboot.mapper.UserMapper;
import com.dayee.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    //或者@Resource
    private UserMapper userMapper;

    @Override
    public int insert(User user) {
        userMapper.insert(user);
        return user.getId();
    }

    @Override
    public List<User> getAll() {
        List<User> list = userMapper.getAll();
        return list;
    }

    @Override
    public User findById(long id) {
        User user = userMapper.findById(id);
        return user;
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void delete(long id) {
        userMapper.delete(id);
    }
}
