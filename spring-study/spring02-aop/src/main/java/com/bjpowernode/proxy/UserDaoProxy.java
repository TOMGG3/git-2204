package com.bjpowernode.proxy;

import com.bjpowernode.dao.UserDao;

import java.util.List;

/**
 * 静态代理
 * 1.仅能为一个目标类服务（缺点）
 * 2.静态代理类（UserDaoProxy）与目标类（UserDaoImpl）必须同时实现同一个父接口
 * 3.编写静态代理的功能
 * 4.调用目标类
 *
 * 缺点：
 * 因为静态代理仅能服务一个目标，当业务类过多时，代理类也会多起来，造成代理类膨胀
 */
public class UserDaoProxy implements UserDao {

    /**
     * 目标类
     */
    private UserDao userDao;
    public UserDaoProxy(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public void insert() {
        //代理的功能
        System.out.println("静态代理日志记录：insert...");
        //目标的功能
        userDao.insert();
    }

    @Override
    public void update() {
        System.out.println("静态代理日志记录：update...");
        userDao.update();

    }

    @Override
    public void delete(Integer id) {
        System.out.println("静态代理日志记录：delete...");
        userDao.delete(id);
    }

    @Override
    public List<String> select() {
        System.out.println("静态代理日志记录：select...");
        return userDao.select();
    }
}
