package com.bjpowernode.service;

import com.bjpowernode.entity.Dept;

import java.util.List;

public interface DeptService {

    List<Dept> list();

    Dept get(Integer id);

    void save(Dept entity);

    void edit(Dept entity);

    void remove(Integer id);
}
