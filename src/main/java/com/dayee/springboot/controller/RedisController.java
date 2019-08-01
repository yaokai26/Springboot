package com.dayee.springboot.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.dayee.springboot.PO.JsonData;
import com.dayee.springboot.PO.User;
import com.dayee.springboot.utils.JsonUtils;
import com.dayee.springboot.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/add")
    public Object add(){
//        redisTemplate.opsForValue().set("name","yaokai");
        boolean success = redisUtils.set("name","yaokai");
        return JsonData.buildSuccess(success);
    }

    @GetMapping("/get")
    public Object get(){
//        String value = redisTemplate.opsForValue().get("name");
        String  value = redisUtils.get("name");
        return JsonData.buildSuccess(value);
    }

    @GetMapping("save_user")
   public Object saveUser(){
        User user = new User(2,26,"jack");
        String userStr = JsonUtils.obj2String(user);
        boolean flag = redisUtils.set("springboot:user:11",userStr);
        return JsonData.buildSuccess(flag);
    }

    @GetMapping("get_user")
    public Object getUser(){
        String value = redisUtils.get("springboot:user:11");
        User user = JsonUtils.string2obj(value,User.class);
        return JsonData.buildSuccess(user);
    }
}
