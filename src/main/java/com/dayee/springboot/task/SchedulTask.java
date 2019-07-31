package com.dayee.springboot.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SchedulTask {

    //每隔两秒执行一个定时任务
//    @Scheduled(fixedRate = 2000)
    @Scheduled(cron = "0 0 */1 * * *")//每小时执行一次
    public void sum(){
        System.out.println("当前时间"+new Date());
    }
}
