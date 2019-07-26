
package com.dayee.springboot.PO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

// 服务器配置
@Component
@PropertySource({ "classpath:application.properties" })
@ConfigurationProperties(prefix = "test")
public class ServerConfig {

//    @Value("${test.name}")
    private String name;

//    @Value("${test.domain}")
    private String domain;

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDomain() {

        return domain;
    }

    public void setDomain(String domain) {

        this.domain = domain;
    }
}
