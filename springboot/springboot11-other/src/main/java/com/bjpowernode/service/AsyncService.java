package com.bjpowernode.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    /**
     * 异步调用
     * 在处理业务时，其中核心业务需在同步调用中完成，
     *              辅助性业务可在异步调用中完成
     */
    @Async
    public void task1(){
        try {
            long start = System.currentTimeMillis();
            //模拟程序耗时
            Thread.sleep(1000);
            long end = System.currentTimeMillis();
            System.out.println("task1耗时：" + (end - start) + "毫秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Async
    public void task2(){
        try {
            long start = System.currentTimeMillis();
            //模拟程序耗时
            Thread.sleep(2000);
            long end = System.currentTimeMillis();
            System.out.println("task2耗时：" + (end - start) + "毫秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Async
    public void task3(){
        try {
            long start = System.currentTimeMillis();
            //模拟程序耗时
            Thread.sleep(3000);
            long end = System.currentTimeMillis();
            System.out.println("task3耗时：" + (end - start) + "毫秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
