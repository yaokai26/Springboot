package com.dayee.springboot.controller;

import com.dayee.springboot.PO.JsonData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    @Value("${test.url}")
    private String url;

    @RequestMapping("/url")
    public Object get(){
        return JsonData.buildSuccess(url);
    }
}
