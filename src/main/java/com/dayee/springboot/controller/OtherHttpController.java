
package com.dayee.springboot.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试post.put.delete请求
 */
@RestController
public class OtherHttpController {

    private Map<String, Object> params = new HashMap<String, Object>();

    /**
     * 测试PostMapping
     * @param name
     * @param pwd
     * @return
     */
    @PostMapping("/v1/login")
    public Object login(String name,String pwd){
        params.clear();
        params.put("name",name);
        params.put("pwd",pwd);
        return params;
    }

    /**
     * 测试PutMapping
     * @param id
     * @return
     */
    @PutMapping("/v1/put")
    public Object put(String id){
        params.clear();
        params.put("id",id);
        return params;
    }

    /**
     * 测试DeleteMapping
     * @param name
     * @return
     */
    @DeleteMapping("/v1/delete")
    public Object delete(String name){
        params.clear();
        params.put("name",name);
        return params;
    }
}
