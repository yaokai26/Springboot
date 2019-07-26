package com.dayee.springboot.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 进入controller之前，通常用于权限校验
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("LoginInterceptor----->preHandle is running");
//        String token = request.getParameter("access_token");
//        response.getWriter().println("fail");

//        System.out.println("LoginInterceptor----->preHandle");
        return HandlerInterceptor.super.preHandle(request,response,handler);
    }

    /**
     * 调用完controller之后，在视图渲染之前
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("LoginInterceptor----->postHandle is running");
        HandlerInterceptor.super.postHandle(request,response, handler,modelAndView);
    }

    /**
     * 整个完成之后，通常用于资源清理
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("LoginInterceptor----->afterCompletion is running");
        HandlerInterceptor.super.afterCompletion(request,response,handler,ex);
    }
}
