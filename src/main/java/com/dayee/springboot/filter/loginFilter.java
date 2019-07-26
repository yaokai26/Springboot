package com.dayee.springboot.filter;


import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//拦截对应的url请求
//@WebFilter(urlPatterns = "/api/*",filterName = "loginFilter")
public class loginFilter implements Filter {
    /**
     * 容器加载的时候调用
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Init loginFilter");
    }

    /**
     * 请求被拦截的时候调用
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter loginFilter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String userNmae = request.getParameter("userName");
        if("yaokai".equals(userNmae)){
            //放行请求
            filterChain.doFilter(request,response);
        }else{
            //不满足条件
            response.sendRedirect("/index.html");
            return;
        }

    }

    /**
     * 容器被销毁的时候被调用
     */
    @Override
    public void destroy() {
        System.out.println("destory loginFilter");
    }
}
