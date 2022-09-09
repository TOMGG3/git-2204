package com.bjpowernode.crm.settings.dao;

import com.bjpowernode.crm.settings.entity.DicType;

import java.util.List;

/**
 * (DicType)表数据库访问层
 *
 * @author makejava
 * @since 2022-08-18 09:08:05
 */
public interface DicTypeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DicType selectById(String id);

    /**
     * 查询指定行数据
     *
     * @param dicType 查询条件
     * @return 对象列表
     */
    List<DicType> select(DicType dicType);

    /**
     * 新增数据
     *
     * @param dicType 实例对象
     * @return 影响行数
     */
    int insert(DicType dicType);

    /**
     * 修改数据
     *
     * @param dicType 实例对象
     * @return 影响行数
     */
    int update(DicType dicType);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    /**
     * 通过主键批量删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteBatch(String[] ids);
}

