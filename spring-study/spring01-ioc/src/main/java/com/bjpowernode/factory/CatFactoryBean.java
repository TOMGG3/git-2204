package com.bjpowernode.factory;

import com.bjpowernode.bean.Cat;
import org.springframework.beans.factory.FactoryBean;

/**
 * FactoryBean方式配置Bean
 *
 */
public class CatFactoryBean implements FactoryBean<Cat> {
    /**
     * 获取对象（将要放置在IOC容器中的对象）的方法
     */
    @Override
    public Cat getObject() throws Exception {
        return new Cat();
    }

    /**
     * 获取Bean的Class类型
     */
    @Override
    public Class<?> getObjectType() {
        return Cat.class;
    }

    /**
     * 设置当前Bean是否为单例
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
}
