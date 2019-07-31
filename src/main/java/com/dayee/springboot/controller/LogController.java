package com.dayee.springboot.controller;

import com.dayee.springboot.PO.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LogController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/log")
    public Object log(){
        logger.debug("this is debug");
        logger.warn("this is warn");
        logger.info("this is info");
        logger.error("this is error");
        return JsonData.buildSuccess(null);
    }
}
