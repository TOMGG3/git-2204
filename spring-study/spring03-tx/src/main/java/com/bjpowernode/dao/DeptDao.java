package com.bjpowernode.dao;


import com.bjpowernode.entity.Dept;

import java.util.List;

public interface DeptDao {

    Dept selectById(Integer id);

    List<Dept> select();

    void insert(Dept entity);

    void update(Dept entity);

    void delete(Integer id);
}
