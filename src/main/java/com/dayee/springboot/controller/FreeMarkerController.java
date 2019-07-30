package com.dayee.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/freemarker")
public class FreeMarkerController {

    @GetMapping("/hello")
    public String index(ModelMap modelMap){
//        return "fm/index.ftl";  //不需要加后缀，配置文件已经指定了后缀
        return "fm/index";
    }

    @GetMapping("/setting")
    public String setting(ModelMap modelMap){
        return "";
    }
}
