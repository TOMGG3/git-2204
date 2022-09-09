package com.bjpowernode.proxy;

// import net.sf.cglib.proxy.Enhancer;
// import net.sf.cglib.proxy.MethodInterceptor;
// import net.sf.cglib.proxy.MethodProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib动态代理
 */
public class CglibProxy implements MethodInterceptor {
    /**
     * 调用目标方法，类似于jdk动态代理中的invoke
     * @param o 代理对象
     * @param method 目标方法
     * @param objects 目标方法参数值数组
     * @param methodProxy 代理方法对象
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib动态代理....代理中实现的功能");

        //执行目标方法
        Object resultVal = method.invoke(targetClass.newInstance(), objects);
        return resultVal;
    }

    private Class targetClass;

    /**
     * 获取代理对象
     */
    public Object getProxy(Class targetClass){
        this.targetClass = targetClass;

        Enhancer enhancer = new Enhancer();
        //设置父类，因为cglib中代理对象与目标对象是父子关系，是目标类的子类
        enhancer.setSuperclass(targetClass);
        //设置回调,调用当前代理类中的intercept方法
        enhancer.setCallback(this);
        //创建代理对象
        return enhancer.create();
    }
    /**
     * jdk动态代理：利用反射机制实现，创建的代理对象与目标对象是兄弟，它们实现同一个父接口
     *          要求：目标类必须为接口+实现类的形式
     * cglib动态代理：利用反射机制实现，创建的代理对象与目标对象是父子关系，
     *              因为在创建代理对象是需要设置父类型，而父类就是目标对象的类型
     *
     *
     */
}
