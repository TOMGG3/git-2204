package com.bjpowernode;

import com.bjpowernode.dao.AccountDao;
import com.bjpowernode.entity.Account;
import com.bjpowernode.entity.Dept;
import com.bjpowernode.service.DeptService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MybatisTests {

    private ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void testGet(){
        DeptService deptService = ioc.getBean(DeptService.class);
        Dept dept = deptService.get(10);
        System.out.println(dept);
    }

    @Test
    public void testList(){
        DeptService deptService = ioc.getBean(DeptService.class);
        List<Dept> list = deptService.list();
        for (Dept dept : list) {
            System.out.println(dept);
        }
    }

    @Test
    public void testPage(){
        DeptService deptService = ioc.getBean(DeptService.class);
        PageInfo pageInfo = deptService.page(2, 2);
        List<Dept> list = pageInfo.getList();
        for (Dept dept : list) {
            System.out.println(dept);
        }
        System.out.println(pageInfo.getTotal());
    }

    @Test
    public void testAccountSelectById(){
        AccountDao accountDao = ioc.getBean("accountDao", AccountDao.class);
        System.out.println(accountDao);
        Account account = accountDao.selectById(1);
        System.out.println(account);
    }

    @Test
    public void testAccountUpdate(){
        AccountDao accountDao = ioc.getBean("accountDao", AccountDao.class);
        Account account = new Account();
        account.setId(2);
        account.setName("京东");
        account.setBalance(110.0);
        accountDao.update(account);
        System.out.println("账号更新成功...");
    }
}
