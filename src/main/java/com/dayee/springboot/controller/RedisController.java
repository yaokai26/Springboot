package com.dayee.springboot.controller;

import com.dayee.springboot.PO.JsonData;
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

    @GetMapping("/add")
    public Object add(){
        redisTemplate.opsForValue().set("name","yaokai");
        return JsonData.buildSuccess("ok");
    }

    @GetMapping("/get")
    public Object get(){
        String value = redisTemplate.opsForValue().get("name");
        return JsonData.buildSuccess(value);
    }
}
