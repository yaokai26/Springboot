
package com.dayee.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

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
public class SpringbootApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringbootApplication.class);
    }

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