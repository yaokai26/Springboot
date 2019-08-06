package com.dayee.springboot.service;


import javax.jms.Destination;

/**
 * 消息生产者
 */
public interface ProducerService {
    /**
     * 指定消息队列还有消息
     * @param destination
     * @param message
     */
    void sendMessage(Destination destination, final String message);

    /**
     * 使用默认消息队列，发送消息
     * @param message
     */
    void sendMessage(final String message);

    /**
     * 消息发布者
     * @param msg
     */
    void publish(String msg);
}
