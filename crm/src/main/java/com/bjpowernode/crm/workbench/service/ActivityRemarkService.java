package com.bjpowernode.crm.workbench.service;


import com.bjpowernode.crm.workbench.entity.ActivityRemark;

import java.util.List;

/**
 * (ActivityRemark)表服务接口
 *
 * @author makejava
 * @since 2022-08-18 09:08:05
 */
public interface ActivityRemarkService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ActivityRemark get(String id);

    /**
     * 列表查询
     * @param activityId 筛选条件
     * @return 用户列表
     */
    List<ActivityRemark> list(String activityId);

    /**
     * 新增数据
     *
     * @param activityRemark 实例对象
     * @return 是否成功
     */
    boolean save(ActivityRemark activityRemark);

    /**
     * 修改数据
     *
     * @param activityRemark 实例对象
     * @return 实例对象
     */
    boolean edit(ActivityRemark activityRemark);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean remove(String id);
}
