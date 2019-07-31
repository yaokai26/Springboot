package com.dayee.springboot.service.impl;

import com.dayee.springboot.PO.User;
import com.dayee.springboot.mapper.UserMapper;
import com.dayee.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    @Override
    @Transactional(//isolation = Isolation.DEFAULT,
            propagation = Propagation.REQUIRED)//传播行为
//    public int addAccount(User user) {
//        userMapper.insert(user);
//        return 0;
//    }
    public int addAccount() {
        User user = new User();
        user.setName("测试事务2");
        user.setAge(26);
        user.setPhone("15810878110");
        user.setCreate_time(new Date());
        userMapper.insert(user);
        int index = 1/0;
        return 0;
    }
}
