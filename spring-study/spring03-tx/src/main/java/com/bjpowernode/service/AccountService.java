package com.bjpowernode.service;

import java.util.Map;

public interface AccountService {

    /**
     * 转账
     * @param fromId 转出账户ID
     * @param toId 转入账户ID
     * @param money 转账金额
     */
    void transferMoney(Integer fromId, Integer toId, Double money) throws Exception;

    /**
     * 支付订单：多次转账
     * @param fromId 支付账户ID
     * @param map key收款账户ID value转账金额
     */
    void payOrder(Integer fromId, Map<Integer, Double> map) throws Exception;
}
