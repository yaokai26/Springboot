package com.dayee.springboot.service;

import com.dayee.springboot.PO.User;

import java.util.List;

public interface UserService {
    int insert(User user);

    List<User> getAll();

    User findById(long id);

    void update(User user);

    void delete(long id);

//    int addAccount(User user);
    int addAccount();
}
