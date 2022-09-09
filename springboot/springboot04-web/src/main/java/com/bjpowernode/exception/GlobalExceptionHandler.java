package com.bjpowernode.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 注解式全局异常处理器
 */
//@ControllerAdvice //同步请求
@RestControllerAdvice //异步请求
public class GlobalExceptionHandler {

    /**
     * 指定异常处理方法
     */

    /**
     * 所有异常处理方法
     */
    @ExceptionHandler(Exception.class)
    public Map<String, Object> exceptionHande(Exception e){
        Map<String, Object> map = new HashMap<>();
        map.put("code", -1);
        map.put("message", "error");
        return map;
    }
}
