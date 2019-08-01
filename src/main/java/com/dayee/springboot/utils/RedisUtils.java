package com.dayee.springboot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtils {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean set(String key,String value){
        redisTemplate.opsForValue().set(key,value);
        return true;
    }

    public  String get(String key){
        String value = redisTemplate.opsForValue().get(key);
        return value;
    }

}
