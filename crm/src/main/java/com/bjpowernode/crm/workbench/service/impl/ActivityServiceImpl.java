package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.commons.utils.DateUtil;
import com.bjpowernode.crm.commons.utils.UUIDUtil;
import com.bjpowernode.crm.workbench.dao.ActivityDao;
import com.bjpowernode.crm.workbench.entity.Activity;
import com.bjpowernode.crm.workbench.service.ActivityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Activity)表服务实现类
 *
 * @author makejava
 * @since 2022-08-18 09:08:05
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Activity get(String id) {
        return activityDao.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param activity 筛选条件
     * @return 查询结果
     */
    @Override
    public Map<String, Object> page(Integer pageNumber, Integer pageSize, Activity activity) {
        PageHelper.startPage(pageNumber, pageSize);
        PageInfo<Activity> pageInfo = new PageInfo<>(activityDao.select(activity));
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("list", pageInfo.getList());
        pageMap.put("total", pageInfo.getTotal());
        return pageMap;
    }

    @Override
    public List<Activity> list(Activity activity) {
        return activityDao.select(activity);
    }

    /**
     * 新增数据
     *
     * @param activity 实例对象
     * @return 是否成功
     */
    @Override
    public boolean save(Activity activity) {
        //手动维护主键值 UUID
        activity.setId(UUIDUtil.getUUID());
        //填写新增和编辑时间
        activity.setCreateTime(DateUtil.dateToString(new Date()));
        activity.setEditTime(DateUtil.dateToString(new Date()));
        return activityDao.insert(activity) > 0;
    }

    /**
     * 修改数据
     *
     * @param activity 实例对象
     * @return 是否成功
     */
    @Override
    public boolean edit(Activity activity) {
        //填写编辑时间
        activity.setEditTime(DateUtil.dateToString(new Date()));
        return activityDao.update(activity) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean remove(String id) {
        return activityDao.deleteById(id) > 0;
    }

    /**
     * 通过主键批量删除数据
     *
     * @param ids 主键
     * @return 是否成功
     */
    @Override
    public boolean removeBatch(String[] ids) {
        return activityDao.deleteBatch(ids) > 0;
    }
}
