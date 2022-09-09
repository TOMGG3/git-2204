package com.bjpowernode;

import com.bjpowernode.dao.StudentDao;
import com.bjpowernode.dao.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class AOPTests {

    @Test
    public void run(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = ioc.getBean("userDao", UserDao.class);
        userDao.insert();
        System.out.println("=============");
        userDao.update();
        System.out.println("=============");
        userDao.delete(10);
        System.out.println("=============");
        List<String> list = userDao.select();
        System.out.println(list);

        System.out.println("=============");
        StudentDao studentDao = ioc.getBean("studentDao", StudentDao.class);
        studentDao.insert();
    }
}
