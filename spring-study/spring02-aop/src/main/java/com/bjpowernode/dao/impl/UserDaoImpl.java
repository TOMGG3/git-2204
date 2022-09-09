package com.bjpowernode.dao.impl;

import com.bjpowernode.dao.UserDao;

import java.util.Arrays;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public void insert() {
        //记录日志，辅助业务
        // System.out.println("日志记录：insert...");
        //模拟JDBC，核心业务
        System.out.println("用户新增操作...");
    }

    @Override
    public void update() {
        // System.out.println("日志记录：update...");

        System.out.println("用户修改操作...");
    }

    @Override
    public void delete(Integer id) {
        // System.out.println("日志记录：delete...");

        System.out.println("用户删除操作..." + id);
        System.out.println(10/id);
    }

    @Override
    public List<String> select() {
        // System.out.println("日志记录：select...");

        System.out.println("用户查询操作...");
        return Arrays.asList("aa", "bb", "cc");
    }
}
