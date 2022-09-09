package com.bjpowernode;

import com.bjpowernode.bean.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DITests {

    @Test
    public void constructor(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        Car car1 = ioc.getBean("car1", Car.class);
        System.out.println(car1);

        Car car2 = ioc.getBean("car2", Car.class);
        System.out.println(car2);
    }

    @Test
    public void setMethod(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = ioc.getBean("stu", Student.class);
        System.out.println(student);
    }

    @Test
    public void ref(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        Orders orders1 = ioc.getBean("orders1", Orders.class);
        System.out.println(orders1);

        Orders orders2 = ioc.getBean("orders2", Orders.class);
        System.out.println(orders2);

        Orders orders3 = ioc.getBean("orders3", Orders.class);
        System.out.println(orders3);
    }

    @Test
    public void autowire(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

        Employee employee = ioc.getBean("employee", Employee.class);
        System.out.println(employee);
    }

    @Test
    public void annotation(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

        Person person = ioc.getBean("person" ,Person.class);
        System.out.println(person);
    }
}
