package com.bjpowernode.crm.settings.dao;

import com.bjpowernode.crm.settings.entity.DicValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (DicValue)表数据库访问层
 *
 * @author makejava
 * @since 2022-08-18 09:08:05
 */
public interface DicValueDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DicValue selectById(String id);

    /**
     * 通过类型编码和字典值查询单条数据
     *
     * @param typeCode 类型编码
     * @param value 字典值
     * @return 实例对象
     */
    DicValue selectByType(@Param("typeCode") String typeCode, @Param("value") String value);

    /**
     * 查询指定行数据
     *
     * @param dicValue 查询条件
     * @return 对象列表
     */
    List<DicValue> select(DicValue dicValue);

    /**
     * 新增数据
     *
     * @param dicValue 实例对象
     * @return 影响行数
     */
    int insert(DicValue dicValue);

    /**
     * 修改数据
     *
     * @param dicValue 实例对象
     * @return 影响行数
     */
    int update(DicValue dicValue);

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

