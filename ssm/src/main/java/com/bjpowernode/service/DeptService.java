package com.bjpowernode.service;

import com.bjpowernode.entity.Dept;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DeptService {

    /**
     * 列表查询
     */
    List<Dept> list();

    /**
     * 分页查询
     * @param pageNumber 当前页码
     * @param pageSize 每页条数
     * @return PageHelper中提供一个分页工具对象
     */
    PageInfo page(Integer pageNumber, Integer pageSize);

    /**
     * 根据ID进行单条记录查询
     */
    Dept get(Integer id);

    /**
     * 新增
     */
    boolean save(Dept entity);

    /**
     * 编辑
     */
    boolean edit(Dept entity);

    /**
     * 删除
     */
    boolean remove(Integer id);
}
