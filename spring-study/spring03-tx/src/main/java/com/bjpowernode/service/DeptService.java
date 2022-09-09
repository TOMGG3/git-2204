package com.bjpowernode.service;

import com.bjpowernode.entity.Dept;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DeptService {

    Dept get(Integer id);

    List<Dept> list();

    PageInfo page(Integer pageNumber, Integer pageSize);

    void save(Dept entity);

    void edit(Dept entity);

    void remove(Integer id);
}
