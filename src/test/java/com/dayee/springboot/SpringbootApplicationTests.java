package com.dayee.springboot;

import com.dayee.springboot.PO.User;
import com.dayee.springboot.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class SpringbootApplicationTests {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void test() {
        User user = new User();
        user.setName("mike");
        user.setAge(25);
        user.setCreate_time(new Date());
        user.setPhone("11010010");
        String userStr = JsonUtils.obj2String(user);
        redisTemplate.opsForValue().set("str",userStr);
        System.out.println(userStr);
    }

}
