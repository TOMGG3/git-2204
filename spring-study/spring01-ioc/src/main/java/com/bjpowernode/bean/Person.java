package com.bjpowernode.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Component注解
 * 位置：类
 * 作用：将标识注解的类的对象放置在IOC容器中
 * 名称：默认是当前类首字母小写
 * 配置项：value为bean设置名称
 *
 * @Controller
 * @Service
 * @Repository
 * 以上三个注解的作用与@Component注解一致
 * 此三个注解分别使用在三层架构上：
 * @Controller 控制层，表现层
 * @Service 服务层，业务层
 * @Repository 持久层
 */
// @Component(value = "per")
@Component
// @Controller
// @Service
// @Repository
public class Person {

    /**
     * @Value
     */
    @Value(value = "10")
    private Integer id;
    @Value(value = "小明")
    private String name;
    @Value(value = "2000/08/15")
    private Date birth;

    /**
     * 自定义类型的属性实现依赖注入，通过注解的方式完成自动装配
     *
     * @Autowired
     * 位置：成员变量
     * 作用：实现自动装配，先根据类型装配，如果类型匹配失败，再根据名称匹配
     * 配置项：required必须的，在当前IOC容器中必须有一个此类型的对象，没有报错
     *
     * required = false 表示IOC容器中有此Bean进行装配，没有不注入，不报错
     *
     * @Resource
     * Java中自定的注解由spring扩展了此注解的解析，使其成为一个自动装配的注解
     * 先根据名称匹配，再根据类型批量
     */
    @Autowired(required = false)
    //手动指定根据名称查找时bean的名称
    @Qualifier("addr")
    // @Resource
    private Address address;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birth=" + birth +
                ", address=" + address +
                '}';
    }
}
