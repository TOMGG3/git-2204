package com.bjpowernode.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *  切面类
 */
@Component
@Aspect
@Order(1)
public class AopAspect {

    public static final String POINT_CUT = "execution(* com.bjpowernode.controller.*.*(..))";

    /**
     * 前置通知
     */
    @Before(POINT_CUT)
    public void beforeMethod(){
        System.out.println("AOP切面的前置通知....");
    }

    /**
     * 后置通知
     */
    @After(POINT_CUT)
    public void afterMethod(){
        System.out.println("AOP切面的后置通知....");
    }

    /**
     * 返回通知
     */
    @AfterReturning(value = POINT_CUT, returning = "resultVal")
    public void beforeMethod(Object resultVal){
        System.out.println("AOP切面的返回通知...." + resultVal);
    }

    /**
     * 异常通知
     */
    @AfterThrowing(value = POINT_CUT, throwing = "ex")
    public void beforeMethod(Exception ex){
        System.out.println("AOP切面的异常通知...." + ex);
    }
}
