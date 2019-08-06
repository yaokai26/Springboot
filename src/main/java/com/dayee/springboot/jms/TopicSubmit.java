package com.dayee.springboot.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 消息订阅者
 */
@Component
public class TopicSubmit {

    @JmsListener(destination = "video.topic",containerFactory="jmsListenerContainerTopic")
    public void receiveTopic(String text){
        System.out.println("TopicConsumer消费者1："+text);
    }

    @JmsListener(destination = "video.topic",containerFactory="jmsListenerContainerTopic")
    public void receiveTopic2(String text){
        System.out.println("TopicConsumer消费者2："+text);
    }

    @JmsListener(destination = "video.topic",containerFactory="jmsListenerContainerTopic")
    public void receiveTopic3(String text){
        System.out.println("TopicConsumer消费者3："+text);
    }
}
