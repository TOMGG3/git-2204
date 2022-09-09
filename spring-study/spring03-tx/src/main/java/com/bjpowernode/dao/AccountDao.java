package com.bjpowernode.dao;

import com.bjpowernode.entity.Account;

public interface AccountDao {

    Account selectById(Integer id);

    void update(Account entity);
}
