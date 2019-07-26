package com.dayee.springboot.Interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * 用来注册拦截器的
 */
@Configuration
public class NewWebMvcConfigurer extends WebMvcConfigurer{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/api2/*/**");
        registry.addInterceptor(new SecondInterceptor()).addPathPatterns("/api2/*/**");
        super.addInterceptors(registry);
    }
}
