package com.dayee.springboot.controller;

import com.dayee.springboot.PO.JsonData;
import com.dayee.springboot.jms.MsgProducer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/v1/rocketmq")
public class RocketMQController {

    @Autowired
    private MsgProducer msgProducer;

    @GetMapping("/order")
    public Object order(String msg,String tag) throws UnsupportedEncodingException, InterruptedException, RemotingException, MQClientException, MQBrokerException {
        /**
         * 创建一个消息实例，包含 topic、tag 和 消息体
         */
        Message message = new Message("testTopic",tag,msg.getBytes(RemotingHelper.DEFAULT_CHARSET));
        SendResult result = msgProducer.getProducer().send(message);
        System.out.println("发送响应:MsgId：" + result.getMsgId()+",发送状态："+result.getSendStatus());
        return JsonData.buildSuccess(null);
    }
}
