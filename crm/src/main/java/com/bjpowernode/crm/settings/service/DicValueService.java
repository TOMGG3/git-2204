package com.bjpowernode.crm.settings.service;

import com.bjpowernode.crm.settings.entity.DicValue;

import java.util.List;
import java.util.Map;

/**
 * (DicValue)表服务接口
 *
 * @author makejava
 * @since 2022-08-18 09:08:05
 */
public interface DicValueService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DicValue get(String id);

    /**
     * 通过类型编码和字典值查询单条数据
     *
     * @param typeCode 类型编码
     * @param value 字典值
     * @return 实例对象
     */
    DicValue getByType(String typeCode, String value);

    /**
     * 分页查询
     *
     * @param pageNumber 当前页码
     * @param pageSize 每页条数
     * @param dicValue 筛选条件
     * @return 查询结果
     */
    Map<String, Object> page(Integer pageNumber, Integer pageSize, DicValue dicValue);

    /**
     * 列表查询
     * @param dicValue 筛选条件
     * @return 用户列表
     */
    List<DicValue> list(DicValue dicValue);

    /**
     * 新增数据
     *
     * @param dicValue 实例对象
     * @return 是否成功
     */
    boolean save(DicValue dicValue);

    /**
     * 修改数据
     *
     * @param dicValue 实例对象
     * @return 实例对象
     */
    boolean edit(DicValue dicValue);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean remove(String id);

    /**
     * 通过主键批量删除数据
     *
     * @param ids 主键数组
     * @return 是否成功
     */
    boolean removeBatch(String[] ids);
}
