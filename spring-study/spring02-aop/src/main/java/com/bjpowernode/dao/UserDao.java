package com.bjpowernode.dao;

import java.util.List;

public interface UserDao {

    void insert();

    void update();

    void delete(Integer id);

    List<String> select();
}
