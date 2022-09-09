package com.bjpowernode.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk的动态代理类
 * 1.实现InvocationHandler接口
 *
 * 通过反射机制，一个代理服务于多个目标
 *
 * JdkProxy此类提供一个getProxy，此方法是提供代理对象（与目标实现同一个父接口）
 */
public class JdkProxy implements InvocationHandler {

    /**
     * invoke执行、调用目标（业务）方法
     * @param proxy 代理对象
     * @param method 目标类中将要执行的目标方法
     * @param args 目标方法的参数值数组
     * @return 目标方法的返回值
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //添加辅助业务
        System.out.println("JDK动态代理前置日志：" + method.getName() + "......");
        //执行业务方法
        Object resultVal = method.invoke(targetClass.newInstance(), args);
        return resultVal;
    }

    /**
     * 目标对象的Class类型
     */
    private Class targetClass;

    /**
     * 获取代理对象的方法
     */
    public Object getProxy(Class targetClass){
        this.targetClass = targetClass;
        /**
         * 创建代理对象
         * 代理对象：与目标类实现同一个父接口的对象
         * 代理类对象：生产代理对象
         *
         * ClassLoader loader 业务类的类加载
         * Class<?>[] interfaces 业务类的父接口
         * InvocationHandler h
         */
        return Proxy.newProxyInstance(targetClass.getClassLoader(),
                targetClass.getInterfaces(), this);
    }
}
