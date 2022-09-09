package com.bjpowernode;

import com.bjpowernode.bean.Book;
import com.bjpowernode.bean.Cat;
import com.bjpowernode.bean.Dog;
import com.bjpowernode.bean.HelloWorld;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCTests {

    @Test
    public void run(){
        //1.初始化IOC容器
        //ClassPathXmlApplicationContext 从类路径下读取配置文件
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

        //2.从IOC容器中获取对象
        /**
         * getBean(String beanName)根据bean名称获取Bean对象
         * getBean(Class beanType) 根据bean类型获取Bean对象,在容器中存在多个此类型Bean，程序报错
         * getBean(String beanName, Class beanType)
         */
        // Object helloworld = ioc.getBean("helloworld");
        HelloWorld helloworld = (HelloWorld) ioc.getBean("helloworld");
        System.out.println(helloworld);
        String result = helloworld.show();
        System.out.println(result);

        HelloWorld h2 = ioc.getBean(HelloWorld.class);
        System.out.println("主bean:" + h2);

        HelloWorld h3 = ioc.getBean("h2", HelloWorld.class);
        System.out.println(h3);
        System.out.println(helloworld == h3);
    }

    @Test
    public void scope(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println("====================分割线====================");
        Book b1 = ioc.getBean("book", Book.class);
        System.out.println(b1);
        Book b2 = ioc.getBean("book", Book.class);
        System.out.println(b2);
        System.out.println(b1 == b2);
    }

    @Test
    public void lifeCycle(){
        System.out.println("===============IOC容器初始化阶段===============");
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("==================对象使用阶段====================");
        Dog dog = ioc.getBean("dog", Dog.class);
        System.out.println(dog);
        System.out.println(dog.getName());

        System.out.println("=================IOC容器关闭阶段======================");
        ioc.close();
    }

    @Test
    public void dependsOn(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void factory(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        Cat cat1 = ioc.getBean("cat1", Cat.class);
        System.out.println(cat1);

        Cat cat2 = ioc.getBean("cat2", Cat.class);
        System.out.println(cat2);

        Object obj = ioc.getBean("cat3");
        System.out.println(obj);
    }
}
