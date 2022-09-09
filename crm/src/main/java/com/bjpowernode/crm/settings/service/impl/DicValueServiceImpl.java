package com.bjpowernode.crm.settings.service.impl;

import com.bjpowernode.crm.commons.exception.CustomException;
import com.bjpowernode.crm.commons.utils.DateUtil;
import com.bjpowernode.crm.commons.utils.MD5Util;
import com.bjpowernode.crm.commons.utils.UUIDUtil;
import com.bjpowernode.crm.settings.dao.DicValueDao;
import com.bjpowernode.crm.settings.entity.DicValue;
import com.bjpowernode.crm.settings.service.DicValueService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (DicValue)表服务实现类
 *
 * @author makejava
 * @since 2022-08-18 09:08:05
 */
@Service
public class DicValueServiceImpl implements DicValueService {

    @Autowired
    private DicValueDao dicValueDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DicValue get(String id) {
        return dicValueDao.selectById(id);
    }

    /**
     * 通过类型编码和字典值查询单条数据
     *
     * @param typeCode 类型编码
     * @param value 字典值
     * @return 实例对象
     */
    @Override
    public DicValue getByType(String typeCode, String value) {
        return dicValueDao.selectByType(typeCode, value);
    }

    /**
     * 分页查询
     *
     * @param dicValue 筛选条件
     * @return 查询结果
     */
    @Override
    public Map<String, Object> page(Integer pageNumber, Integer pageSize, DicValue dicValue) {
        PageHelper.startPage(pageNumber, pageSize);
        PageInfo<DicValue> pageInfo = new PageInfo<>(dicValueDao.select(dicValue));
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("list", pageInfo.getList());
        pageMap.put("total", pageInfo.getTotal());
        return pageMap;
    }

    @Override
    public List<DicValue> list(DicValue dicValue) {
        return dicValueDao.select(dicValue);
    }

    /**
     * 新增数据
     *
     * @param dicValue 实例对象
     * @return 是否成功
     */
    @Override
    public boolean save(DicValue dicValue) {
        //判断在同一个字典类型编码下的字典值不能重复
        DicValue dbDicValue = dicValueDao.selectByType(dicValue.getTypeCode(), dicValue.getValue());
        if(dbDicValue != null){
            //抛出异常
            throw new CustomException(-4, "数据字典类型的字典值已存在");
        }

        //手动维护主键值 UUID
        dicValue.setId(UUIDUtil.getUUID());
        return dicValueDao.insert(dicValue) > 0;
    }

    /**
     * 修改数据
     *
     * @param dicValue 实例对象
     * @return 是否成功
     */
    @Override
    public boolean edit(DicValue dicValue) {
        return dicValueDao.update(dicValue) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean remove(String id) {
        return dicValueDao.deleteById(id) > 0;
    }

    /**
     * 通过主键批量删除数据
     *
     * @param ids 主键
     * @return 是否成功
     */
    @Override
    public boolean removeBatch(String[] ids) {
        return dicValueDao.deleteBatch(ids) > 0;
    }
}
