package com.dayee.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 *  @RestController 相当于 @Controller + @ResponseBody,支持Json类型的返回
 */
@RestController
//@Controller
public class HelloController {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello World!";
    }

    @RequestMapping("/test")
//       @ResponseBody  @Controller注解不加@ResponseBody,报错无法返回数据
    public Map<String,String> test(){
        Map<String,String> map = new HashMap<>();
        map.put("name","姚凯");
        map.put("sex","女");
        return map;
    }
}
