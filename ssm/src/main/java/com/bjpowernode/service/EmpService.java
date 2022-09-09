package com.bjpowernode.service;

import com.bjpowernode.entity.Emp;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface EmpService {

    /**
     * 列表查询
     */
    List<Emp> list();

    /**
     * 分页查询
     * @param pageNumber 当前页码
     * @param pageSize 每页条数
     * @return Map集合，其中存储前端页面需要数据（当前页面列表数据，总记录数）
     */
    Map<String, Object> page(Integer pageNumber, Integer pageSize, Emp entity);

    /**
     * 根据ID进行单条记录查询
     */
    Emp get(Integer id);

    /**
     * 新增
     */
    boolean save(Emp entity);

    /**
     * 编辑
     */
    boolean edit(Emp entity);

    /**
     * 删除
     */
    boolean remove(Integer id);

    /**
     * 批量删除
     */
    boolean removeBatch(Integer[] ids);
}
