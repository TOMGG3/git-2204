package com.bjpowernode.dao;

import com.bjpowernode.entity.Dept;

import java.util.List;

public interface DeptDao {

    List<Dept> select();

    Dept selectById(Integer id);

    void insert(Dept entity);

    void update(Dept entity);

    void deleteById(Integer id);
}
