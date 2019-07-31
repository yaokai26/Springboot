package com.dayee.springboot.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * 异步任务业务类
 */
@Component
@Async
public class AsyncTask {

    public void task1() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread.sleep(1000L);
        long endTime = System.currentTimeMillis();
        System.out.println("任务1耗时"+(endTime-startTime));
    }

    public void task2() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread.sleep(2000L);
        long endTime = System.currentTimeMillis();
        System.out.println("任务2耗时"+(endTime-startTime));
    }

    public void task3() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread.sleep(3000L);
        long endTime = System.currentTimeMillis();
        System.out.println("任务3耗时"+(endTime-startTime));
    }

    //获取异步结果

    public Future<String> task4() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread.sleep(1000L);
        long endTime = System.currentTimeMillis();
        System.out.println("任务4耗时"+(endTime-startTime));
        return new AsyncResult<String>("任务4");
    }

    public Future<String> task5() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread.sleep(2000L);
        long endTime = System.currentTimeMillis();
        System.out.println("任务5耗时"+(endTime-startTime));
        return new AsyncResult<String>("任务5");
    }

    public Future<String> task6() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread.sleep(3000L);
        long endTime = System.currentTimeMillis();
        System.out.println("任务6耗时"+(endTime-startTime));
        return new AsyncResult<String>("任务4");
    }
}
