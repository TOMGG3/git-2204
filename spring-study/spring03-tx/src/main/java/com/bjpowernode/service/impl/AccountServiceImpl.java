package com.bjpowernode.service.impl;

import com.bjpowernode.dao.AccountDao;
import com.bjpowernode.entity.Account;
import com.bjpowernode.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

/**
 * 业务层，事务层
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;
    //自己注入自己
    @Autowired
    private AccountService accountService;

    /**
     * 转账业务
     * 1.查询转出账户信息
     * 2.更新转出账户信息，从账户扣除金额
     * 3.查询转入账户信息
     * 4.更新转入账户信息，将转账金额加到余额中
     */
    @Override
    public void transferMoney(Integer fromId, Integer toId, Double money) throws Exception {
        Account fromAccount = accountDao.selectById(fromId);

        fromAccount.setBalance(fromAccount.getBalance() - money);
        accountDao.update(fromAccount);
        System.out.println("account更新成功...");

        //模拟异常，运行时异常
        //spring中默认仅回滚运行时异常,编译期异常不回滚
        // String str = null;
        // str.trim();
        // Class.forName("abc");
        //模拟事务耗时
        Thread.sleep(5000);

        Account toAccount = accountDao.selectById(toId);

        toAccount.setBalance(toAccount.getBalance() + money);
        accountDao.update(toAccount);
        System.out.println("account更新成功...");

        System.out.println("一次转账完成...");
    }

    @Override
    public void payOrder(Integer fromId, Map<Integer, Double> map) throws Exception {

        Set<Integer> toIdSet = map.keySet();
        for (Integer toId : toIdSet) {
            //获取转账金额
            Double money = map.get(toId);
            //调用转账方法，实现一次转账
            //在spring中如果一个事务方法调用自己类中的事务方法，无论事务传播配置的是什么，都会以REQUIRED来执行
            accountService.transferMoney(fromId, toId, money);

            //模拟异常
            System.out.println(10/0);
        }
        System.out.println("订单支付成功...");
    }
}
