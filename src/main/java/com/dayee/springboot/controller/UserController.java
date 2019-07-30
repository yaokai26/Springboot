package com.dayee.springboot.controller;

import com.dayee.springboot.PO.JsonData;
import com.dayee.springboot.PO.User;
import com.dayee.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/add")
    public Object add(){
        User user = new User();
        user.setName("yk");
        user.setAge(26);
        user.setPhone("1377527000");
        user.setCreateDate(new Date());
        int id = userService.add(user);
        return JsonData.buildSuccess(id);
    }
}
