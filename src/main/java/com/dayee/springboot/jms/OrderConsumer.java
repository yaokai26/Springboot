package com.dayee.springboot.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * p2p消息消费者
 */
@Component
public class OrderConsumer {

    /**
     * 实时监听实时消费 消息队列里的消息
     * @param text
     */
    @JmsListener(destination = "order.queue")
    public void receiveQueue(String text){
        System.out.println("OrderConsumer收到的报文为"+text);
    }
}
