package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.entity.ActivityRemark;

import java.util.List;

/**
 * (ActivityRemark)表数据库访问层
 *
 * @author makejava
 * @since 2022-08-18 09:08:05
 */
public interface ActivityRemarkDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ActivityRemark selectById(String id);

    /**
     * 查询指定行数据
     *
     * @param activityId 市场活动ID
     * @return 对象列表
     */
    List<ActivityRemark> select(String activityId);

    /**
     * 新增数据
     *
     * @param activityRemark 实例对象
     * @return 影响行数
     */
    int insert(ActivityRemark activityRemark);

    /**
     * 修改数据
     *
     * @param activityRemark 实例对象
     * @return 影响行数
     */
    int update(ActivityRemark activityRemark);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);
}

