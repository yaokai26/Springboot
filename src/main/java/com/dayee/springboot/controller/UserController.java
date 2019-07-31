package com.dayee.springboot.controller;

import com.dayee.springboot.PO.JsonData;
import com.dayee.springboot.PO.User;
import com.dayee.springboot.mapper.UserMapper;
import com.dayee.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired(required = false)
    //或者@Resource
    private UserMapper userMapper;

    @GetMapping("/add")
    public Object add(){
        User user = new User();
        user.setName("yaokai");
        user.setAge(26);
        user.setPhone("15810878110");
        user.setCreate_time(new Date());
        int id = userService.insert(user);
        return JsonData.buildSuccess(id);
    }

    @GetMapping("/findAll")
    public Object findAll(){
        return JsonData.buildSuccess(userService.getAll());
    }

    @GetMapping("/findById")
    public Object findById(long id){
        return JsonData.buildSuccess(userService.findById(id));
    }

    @GetMapping("/update")
    public Object update(String name,int id){
        User user = new User();
        user.setId(id);
        user.setName(name);
        userService.update(user);
        return JsonData.buildSuccess(null);
    }

    @GetMapping("/delete")
    public Object delete(long id){
        userService.delete(id);
        return JsonData.buildSuccess(null);
    }

    @GetMapping("/addAccount")
//    @Transactional(propagation = Propagation.REQUIRED)
//    public Object addAccount(){
//        User user = new User();
//        user.setName("测试事务");
//        user.setAge(26);
//        user.setPhone("15810878110");
//        user.setCreate_time(new Date());
//        userService.addAccount(user);
//        int index  = 1/0;
//        return JsonData.buildException(null);
//    }
    public Object addAccount(){
        userService.addAccount();
        return JsonData.buildSuccess(null);
    }
}
