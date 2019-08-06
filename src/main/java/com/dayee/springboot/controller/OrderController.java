package com.dayee.springboot.controller;

import com.dayee.springboot.PO.JsonData;
import com.dayee.springboot.service.ProducerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;

@RestController
@RequestMapping("/api/v1/activeMQ")
public class OrderController {

    @Autowired
    private ProducerService producerService;

    @RequestMapping("/order")
    public Object order(String msg){
        //生成消息队列地址
        Destination destination = new ActiveMQQueue("order.queue");
        producerService.sendMessage(destination,msg);
        return JsonData.buildSuccess(null);
    }

    @RequestMapping("/common")
    public Object common(String msg){
        producerService.sendMessage(msg);
        return JsonData.buildSuccess(null);
    }

    /**
     * 发布消息，生产者
     * @param msg
     * @return
     */
    @RequestMapping("/publish")
    public Object publish(String msg){
        producerService.publish(msg);
        return JsonData.buildSuccess(null);
    }
}
