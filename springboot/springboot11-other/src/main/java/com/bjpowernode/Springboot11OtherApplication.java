package com.bjpowernode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//开启异步调用
@EnableAsync
//开启定时任务
@EnableScheduling
public class Springboot11OtherApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot11OtherApplication.class, args);
    }

}
