package com.dayee.springboot.controller;

import com.dayee.springboot.PO.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

    @Autowired
    private ServerConfig serverConfig;

    @GetMapping("hello")
    public Object hello() {
        return "index";//不用加后缀,配置文件中已经指定了后缀
        //此处相当于取application.properties的spring.thymeleaf.prefix=classpath:/templates/tl/
        //找到tl下的index.html文件
    }

    @GetMapping("info")
    public String getInfo(ModelMap modelMap){
        modelMap.addAttribute("config",serverConfig);
        return "admin/info";
    }
}
