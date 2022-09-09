package com.bjpowernode.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 切面类
 * 功能：记录日志
 * 类似于动态代理中代理类
 * 1.无需实现任何接口
 * 2.无需使用反射机制
 */
// @Component
// @Aspect
// @Order(1)
public class LogAspect {

    /**
     * 通知方法
     */
    // @Before("execution(* com.bjpowernode.dao.impl.StudentDaoImpl.*(..))")
    public void beforeMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("AOP日志记录：前置通知..." + methodName);
    }

    // @After("execution(* com.bjpowernode.dao.impl.StudentDaoImpl.*(..))")
    public void afterMethod(){
        System.out.println("AOP日志记录：后置通知...");
    }

    /**
     * 返回通知：可以获取到目标方法的返回值
     */
    // @AfterReturning(value = "execution(* com.bjpowernode.dao.impl.StudentDaoImpl.*(..))", returning = "result")
    public void returnMethod(Object result){
        System.out.println("AOP日志记录：返回通知..." + result);
    }

    // @AfterThrowing(value = "execution(* com.bjpowernode.dao.impl.StudentDaoImpl.*(..))", throwing = "ex")
    public void throwMethod(Exception ex){
        System.out.println("AOP日志记录：异常通知..." + ex);
    }

    /**
     * 环绕通知
     */
    // @Around("execution(* com.bjpowernode.dao.impl.StudentDaoImpl.*(..))")
    public void aroundMethod(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("环绕通知中的前置...");
        try {
            //需要手动去调用目标方法
            Object result = proceedingJoinPoint.proceed();
            System.out.println("环绕通知中的返回..." + result);
        } catch (Throwable e) {
            System.out.println("环绕通知中的异常..." + e);
        }
        System.out.println("环绕通知中的后置...");
    }
}
