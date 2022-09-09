package com.bjpowernode.crm.settings.dao;

import com.bjpowernode.crm.settings.entity.User;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2022-08-18 09:08:05
 */
public interface UserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User selectById(String id);

    /**
     * 通过用户账号查询单条数据
     *
     * @param loginAct 用户账号
     * @return 实例对象
     */
    User selectByLoginAct(String loginAct);

    /**
     * 查询指定行数据
     *
     * @param user 查询条件
     * @return 对象列表
     */
    List<User> select(User user);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(User user);

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

