package com.bjpowernode.crm.settings.service;

import com.bjpowernode.crm.settings.entity.User;

import java.util.List;
import java.util.Map;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2022-08-18 09:08:05
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User get(String id);

    /**
     * 通过登陆账号查询单条数据
     *
     * @param loginAct 主键
     * @return 实例对象
     */
    User getByLoginAct(String loginAct);

    /**
     * 分页查询
     *
     * @param pageNumber 当前页码
     * @param pageSize 每页条数
     * @param user 筛选条件
     * @return 查询结果
     */
    Map<String, Object> page(Integer pageNumber, Integer pageSize, User user);

    /**
     * 列表查询
     * @param user 筛选条件
     * @return 用户列表
     */
    List<User> list(User user);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 是否成功
     */
    boolean save(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    boolean edit(User user);

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
