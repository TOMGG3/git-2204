package com.bjpowernode;

import com.bjpowernode.dao.StudentDao;
import com.bjpowernode.dao.UserDao;
import com.bjpowernode.dao.impl.StudentDaoImpl;
import com.bjpowernode.dao.impl.UserDaoImpl;
import com.bjpowernode.proxy.CglibProxy;
import com.bjpowernode.proxy.JdkProxy;
import com.bjpowernode.service.UserService;
import com.bjpowernode.service.impl.UserServiceImpl;
import org.junit.Test;

import java.util.List;

public class ProxyTests {
    @Test
    public void run(){
        UserService userService = new UserServiceImpl();
        userService.save();
        System.out.println("------------");
        userService.edit();
        System.out.println("------------");
        userService.remove(10);
        System.out.println("------------");
        List<String> list = userService.list();
        System.out.println(list);
    }

    @Test
    public void jdkProxy(){
        UserDao userDao = (UserDao) new JdkProxy().getProxy(UserDaoImpl.class);
        userDao.insert();
        System.out.println("------------");
        userDao.update();
        System.out.println("------------");
        userDao.delete(10);
        System.out.println("------------");
        List<String> list = userDao.select();
        System.out.println(list);

        System.out.println("------------");
        StudentDao studentDao = (StudentDao) new JdkProxy().getProxy(StudentDaoImpl.class);
        studentDao.insert();
    }

    @Test
    public void cglib(){
        UserDao userDao = (UserDao) new CglibProxy().getProxy(UserDaoImpl.class);
        userDao.insert();
        System.out.println("------------");
        userDao.update();
        System.out.println("------------");
        userDao.delete(10);
        System.out.println("------------");
        List<String> list = userDao.select();
        System.out.println(list);

        System.out.println("------------");
        StudentDao studentDao = (StudentDao) new CglibProxy().getProxy(StudentDaoImpl.class);
        studentDao.insert();
    }
}
