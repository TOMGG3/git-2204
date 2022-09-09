package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.commons.utils.DateUtil;
import com.bjpowernode.crm.commons.utils.UUIDUtil;
import com.bjpowernode.crm.workbench.dao.ActivityRemarkDao;
import com.bjpowernode.crm.workbench.entity.ActivityRemark;
import com.bjpowernode.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * (ActivityRemark)表服务实现类
 *
 * @author makejava
 * @since 2022-08-18 09:08:05
 */
@Service
public class ActivityRemarkServiceImpl implements ActivityRemarkService {

    @Autowired
    private ActivityRemarkDao activityRemarkDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ActivityRemark get(String id) {
        return activityRemarkDao.selectById(id);
    }

    @Override
    public List<ActivityRemark> list(String activityId) {
        return activityRemarkDao.select(activityId);
    }

    /**
     * 新增数据
     *
     * @param activityRemark 实例对象
     * @return 是否成功
     */
    @Override
    public boolean save(ActivityRemark activityRemark) {
        //手动维护主键值 UUID
        activityRemark.setId(UUIDUtil.getUUID());
        //填写新增和编辑时间
        activityRemark.setCreateTime(DateUtil.dateToString(new Date()));
        activityRemark.setEditTime(DateUtil.dateToString(new Date()));
        return activityRemarkDao.insert(activityRemark) > 0;
    }

    /**
     * 修改数据
     *
     * @param activityRemark 实例对象
     * @return 是否成功
     */
    @Override
    public boolean edit(ActivityRemark activityRemark) {
        //填写编辑时间
        activityRemark.setEditTime(DateUtil.dateToString(new Date()));
        return activityRemarkDao.update(activityRemark) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean remove(String id) {
        return activityRemarkDao.deleteById(id) > 0;
    }
}
