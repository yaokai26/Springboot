package com.dayee.springboot.listener;

import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("===========requestDestory===========");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("===========requestInitialize===========");
    }
}
