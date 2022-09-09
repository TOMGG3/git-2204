package com.bjpowernode.dao;

import com.bjpowernode.entity.Emp;

import java.util.List;

public interface EmpDao {
    /**
     * 批量查询
     */
    List<Emp> select(Emp entity);

    /**
     * 单条查询
     */
    Emp selectById(Integer id);

    /**
     * 新增
     */
    int insert(Emp entity);

    /**
     * 修改
     */
    int update(Emp entity);

    /**
     * 删除
     */
    int delete(Integer id);

    /**
     * 批量删除
     */
    int deleteBatch(Integer[] ids);
}
