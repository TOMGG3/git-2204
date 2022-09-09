package com.bjpowernode.service.impl;

import com.bjpowernode.dao.UserDao;
import com.bjpowernode.dao.impl.UserDaoImpl;
import com.bjpowernode.proxy.UserDaoProxy;
import com.bjpowernode.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    //静态代理模式
    // private UserDao userDao = new UserDaoImpl();
    private UserDao userDao = new UserDaoProxy(new UserDaoImpl());

    @Override
    public void save() {
        System.out.println("UserService..save...");
        userDao.insert();
    }

    @Override
    public void edit() {
        System.out.println("UserService..edit...");
        userDao.update();
    }

    @Override
    public void remove(Integer id) {
        System.out.println("UserService..remove..." + id);
        userDao.delete(id);
    }

    @Override
    public List<String> list() {
        System.out.println("UserService..list...");
        return userDao.select();
    }
}
