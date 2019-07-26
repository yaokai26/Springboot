package com.dayee.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dayee.springboot.PO.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dayee.springboot.PO.User;

@RestController
public class GetController {

    private Map<String,Object> params = new HashMap<>();

    /**
     * 功能描述：测试restful协议,从路径中获取字段
     * @param cityId
     * @param userId
     * @return
     */
//    @RequestMapping(path = "/{city_id}/{user_id}", method = RequestMethod.GET)
//    public Object findUser(@PathVariable("city_id") String cityId, @PathVariable("user_id") String userId) {
//
//        params.clear();
//        params.put("cityId", cityId);
//        params.put("userId", userId);
//        return params;
//    }

    /**
     * 测试GetMapping
     * @param from
     * @param size
     * @return
     */
    @GetMapping(path = "/v1/page_user1")
    public Object pageUser(int from, int size) {

        params.clear();
        params.put("from", from);
        params.put("size", size);
        return params;
    }


    /**
     * 参数不传，取默认值
     * @param from
     * @param size
     * @return
     */
    @GetMapping(path = "/v1/page_user2")
    public Object pageUserv2(@RequestParam(defaultValue = "0", name = "page") int from, int size) {

        params.clear();
        params.put("from", from);
        params.put("size", size);
        return params;
    }

    /**
     * bean对象传参(post方式)
     * 注意需要指定传参content-type为application/json
     * 使用body传递数据
     * @param user
     * @return
     */
    @RequestMapping("/v1/save_user")
    public Object saveUser(@RequestBody User user) {

        params.clear();
        params.put("user", user);
        return params;
    }

    /**
     * 测试获取http请求头
     * @param accessToken
     * @param id
     * @return
     */
    @GetMapping("/v1/get_header")
    public Object getHeader(@RequestHeader("access_token") String accessToken, String id) {

        params.clear();
        params.put("access_token", accessToken);
        params.put("id", id);
        return params;
    }

    @GetMapping("/v1/test_request")
    public Object testRequest(HttpServletRequest request){
        params.clear();
        String id = request.getParameter("id");
        params.put("id",id);
        return params;
    }

    @GetMapping("/va/{user_id}")
    public Object getUser(@PathVariable("user_id") String one,
                          @RequestParam(defaultValue = "2", name = "param") String param,
                          String id,
                          @RequestBody User user) {

        params.clear();
        params.put("one", one);
        params.put("param", param);
        params.put("id", id);
        params.put("user", user);
        return params;
    }

    @Autowired
    private ServerConfig serverConfig;

    @GetMapping("/v1/test_property")
    public Object testProperty(){

        return serverConfig;
    }

    @GetMapping("/api2/v1/account")
    public Object login(){
        System.out.println("in controller-----> controller is running ");
        params.put("name","yk1");
        params.put("sex","男");
        return params;
    }
}
