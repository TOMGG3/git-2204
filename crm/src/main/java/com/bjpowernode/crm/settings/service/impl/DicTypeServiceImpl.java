package com.bjpowernode.crm.settings.service.impl;

import com.bjpowernode.crm.commons.exception.CustomException;
import com.bjpowernode.crm.settings.dao.DicTypeDao;
import com.bjpowernode.crm.settings.entity.DicType;
import com.bjpowernode.crm.settings.service.DicTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (DicType)表服务实现类
 *
 * @author makejava
 * @since 2022-08-18 09:08:05
 */
@Service
public class DicTypeServiceImpl implements DicTypeService {

    @Autowired
    private DicTypeDao dicTypeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DicType get(String id) {
        return dicTypeDao.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param dicType 筛选条件
     * @return 查询结果
     */
    @Override
    public Map<String, Object> page(Integer pageNumber, Integer pageSize, DicType dicType) {
        PageHelper.startPage(pageNumber, pageSize);
        PageInfo<DicType> pageInfo = new PageInfo<>(dicTypeDao.select(dicType));
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("list", pageInfo.getList());
        pageMap.put("total", pageInfo.getTotal());
        return pageMap;
    }

    @Override
    public List<DicType> list(DicType dicType) {
        return dicTypeDao.select(dicType);
    }

    /**
     * 新增数据
     *
     * @param dicType 实例对象
     * @return 是否成功
     */
    @Override
    public boolean save(DicType dicType) {
        //判断code是否重复
        DicType dbDicType = dicTypeDao.selectById(dicType.getCode());
        if(dbDicType != null){
            throw new CustomException(-3, "数据字典类型编码已存在");
        }
        return dicTypeDao.insert(dicType) > 0;
    }

    /**
     * 修改数据
     *
     * @param dicType 实例对象
     * @return 是否成功
     */
    @Override
    public boolean edit(DicType dicType) {
        return dicTypeDao.update(dicType) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean remove(String id) {
        return dicTypeDao.deleteById(id) > 0;
    }

    /**
     * 通过主键批量删除数据
     *
     * @param ids 主键
     * @return 是否成功
     */
    @Override
    public boolean removeBatch(String[] ids) {
        return dicTypeDao.deleteBatch(ids) > 0;
    }
}
