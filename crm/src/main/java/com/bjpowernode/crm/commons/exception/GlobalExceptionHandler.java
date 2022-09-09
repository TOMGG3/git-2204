package com.bjpowernode.crm.commons.exception;

import com.bjpowernode.crm.commons.utils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @ControllerAdvice 处理同步请求的异常
 * @RestControllerAdvice 处理异步请求的异常 = @ControllerAdvice + @ResponseBody
 */
//@ControllerAdvice
//@ResponseBody
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public Result handleCustomException(CustomException e){
        return Result.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        System.out.println(e);
        return Result.error(-1, e.getMessage());
    }
}