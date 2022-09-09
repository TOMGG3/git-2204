package com.bjpowernode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/**
 * 代理扫描注解
 */
//@MapperScan(basePackages = {"com.bjpowernode.dao", "com.bjpowernode.dao2"})
public class Springboot09MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot09MybatisApplication.class, args);
    }

}
