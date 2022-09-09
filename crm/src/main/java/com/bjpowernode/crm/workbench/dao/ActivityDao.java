package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.entity.Activity;

import java.util.List;

/**
 * (Activity)表数据库访问层
 *
 * @author makejava
 * @since 2022-08-18 09:08:05
 */
public interface ActivityDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Activity selectById(String id);

    /**
     * 查询指定行数据
     *
     * @param activity 查询条件
     * @return 对象列表
     */
    List<Activity> select(Activity activity);

    /**
     * 新增数据
     *
     * @param activity 实例对象
     * @return 影响行数
     */
    int insert(Activity activity);

    /**
     * 修改数据
     *
     * @param activity 实例对象
     * @return 影响行数
     */
    int update(Activity activity);

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

