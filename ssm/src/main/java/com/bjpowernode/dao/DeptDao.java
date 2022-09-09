package com.bjpowernode.dao;

import com.bjpowernode.entity.Dept;

import java.util.List;

public interface DeptDao {
    /**
     * 批量查询
     */
    List<Dept> select();

    /**
     * 单条查询
     */
    Dept selectById(Integer id);

    /**
     * 新增
     */
    int insert(Dept entity);

    /**
     * 修改
     */
    int update(Dept entity);

    /**
     * 删除
     */
    int delete(Integer id);
}
