
package com.dayee.springboot;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;
import javax.servlet.MultipartConfigElement;
import javax.servlet.annotation.MultipartConfig;
import java.net.MalformedURLException;

//@SpringBootApplication
//public class SpringbootApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(SpringbootApplication.class, args);
//    }
//
//    @Bean
//    public MultipartConfigElement multipartConfigElement(){
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        //设置单个文件最大
//        factory.setMaxFileSize("102400KB");//硬编码 后期可以放在配置文件里
//        //设置总上传文件大小
//        factory.setMaxRequestSize("102400000KB");
//        return factory.createMultipartConfig();
//    }
//}
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.dayee.springboot.mapper")
@EnableScheduling //开启定时任务
@EnableAsync//开启异步任务
@EnableElasticsearchRepositories(basePackages = "com.dayee.springboot.respository")
@EnableJms
public class SpringbootApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringbootApplication.class);
    }

    @Bean //交给spring管理，方便后续注入
    public Queue queue(){
        return new ActiveMQQueue("myQueue.queue");
    }

    /**
     * 主题对象交给spring管理
     * @return
     */
    @Bean
    public Topic topic(){
        return new ActiveMQTopic("video.topic");
    }

    //在配置文件里面，注释掉 #spring.jms.pub-sub-domain=true
    //需要给topic定义独立的JmsListenerContainer,支持p2p和发布订阅模式同时存在
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(activeMQConnectionFactory);
        return bean;
    }

    //在配置文件里面，注释掉 #spring.jms.pub-sub-domain=true

    public static void main(String[] args) {

        SpringApplication.run(SpringbootApplication.class, args);
    }

    //只需要把该方法放在有@Configuration的类就行
    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //设置单个文件最大
        factory.setMaxFileSize("102400KB");//硬编码 后期可以放在配置文件里
        //设置总上传文件大小
        factory.setMaxRequestSize("102400000KB");
        return factory.createMultipartConfig();
    }
}