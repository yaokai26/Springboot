package com.dayee.springboot.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * p2p消息消费者
 */
@Component
public class CommonConsumer {

    @JmsListener(destination = "myQueue.queue")
    public void receiveMessage(String text){
        System.out.println("CommonConsumer接收到的报文为"+text);
    }
}
