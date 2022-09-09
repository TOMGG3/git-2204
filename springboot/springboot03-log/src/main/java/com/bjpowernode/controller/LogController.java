package com.bjpowernode.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//注解式日志: 在当前类中动态生成一个静态的日志对象，名称为log
@Slf4j
public class LogController {

    /**
     * 使用日志：
     * 在当前类中声明一个Logger的成员变量
     */
//    private Logger log = LoggerFactory.getLogger(LogController.class);

    @GetMapping("/getLog")
    public String getLog(){
        /**
         * 在各个日志框架都有日志级别
         * 错误error, 警告warn, 信息info, 调试debug, 底层trace
         */
        //程序出现异常
        log.error("error级别的日志...");
        //程序有可能出现问题，提醒或警告
        log.warn("warn级别的日志...");
        //程序做记录
        log.info("info级别的日志...");
        //程序调试
        log.debug("debug级别的日志...");
        //程序底层信息
        log.trace("trace级别的日志...");

        //热部署即时生效: devtools + ctrl f9
        log.info("hahaha....");
        log.info("xixixi....");

        return "正在记录日志...";
    }
}
