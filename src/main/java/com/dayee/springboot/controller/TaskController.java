package com.dayee.springboot.controller;

import com.dayee.springboot.PO.JsonData;
import com.dayee.springboot.task.AsyncTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

/**
 * 异步执行task1、task2、task3
 */
@RestController
@RequestMapping("/api/v1")
public class TaskController {

    @Autowired
    private AsyncTask asyncTask;

    @GetMapping("/async")
    public JsonData executeTask() throws InterruptedException {
        long start = System.currentTimeMillis();
        //不需要返回结果
//        asyncTask.task1();
//        asyncTask.task2();
//        asyncTask.task3();


        //获取返回结果，取最长的执行结果
        Future<String> task4 = asyncTask.task4();
        Future<String> task5 = asyncTask.task5();
        Future<String> task6 = asyncTask.task6();
        for(;;){
            if(task4.isDone() && task5.isDone() && task6.isDone()){
                break;
            }
        }


        long end = System.currentTimeMillis();
        System.out.println("执行耗时"+(end-start));
        return JsonData.buildSuccess(end-start);
    }
}
