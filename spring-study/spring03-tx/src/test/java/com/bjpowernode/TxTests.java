package com.bjpowernode;

import com.bjpowernode.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class TxTests {

    @Test
    public void run() throws Exception {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = ioc.getBean(AccountService.class);
        System.out.println(accountService);

        accountService.transferMoney(1, 2, 299.0);
    }

    @Test
    public void run2() throws Exception {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = ioc.getBean(AccountService.class);

        Map<Integer, Double> map = new HashMap<>();
        map.put(2, 130.0);
        map.put(3, 399.0);
        accountService.payOrder(1, map);
    }
}
