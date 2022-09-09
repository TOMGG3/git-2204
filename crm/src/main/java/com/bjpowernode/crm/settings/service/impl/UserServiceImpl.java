package com.bjpowernode.crm.settings.service.impl;

import com.bjpowernode.crm.commons.exception.CustomException;
import com.bjpowernode.crm.commons.utils.DateUtil;
import com.bjpowernode.crm.commons.utils.MD5Util;
import com.bjpowernode.crm.commons.utils.UUIDUtil;
import com.bjpowernode.crm.settings.entity.DicValue;
import com.bjpowernode.crm.settings.entity.User;
import com.bjpowernode.crm.settings.dao.UserDao;
import com.bjpowernode.crm.settings.service.DicValueService;
import com.bjpowernode.crm.settings.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2022-08-18 09:08:05
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private DicValueService dicValueService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User get(String id) {
        return userDao.selectById(id);
    }

    /**
     * 通过登陆账号查询单条数据
     *
     * @param loginAct 主键
     * @return 实例对象
     */
    @Override
    public User getByLoginAct(String loginAct) {
        return userDao.selectByLoginAct(loginAct);
    }

    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @return 查询结果
     */
    @Override
    public Map<String, Object> page(Integer pageNumber, Integer pageSize, User user) {
        PageHelper.startPage(pageNumber, pageSize);
        PageInfo<User> pageInfo = new PageInfo<>(userDao.select(user));
        Map<String, Object> pageMap = new HashMap<>();
        //当前页数据: 做数据处理
        //jdk7
//        List<User> list = pageInfo.getList();
//        for (User u : list) {
//            DicValue dicValue = dicValueService.getByType("lockState", u.getLockState());
//            u.setLockState(dicValue.getText());
//        }
        //jdk8
        pageMap.put("list", pageInfo.getList().stream().map(item -> {
            DicValue dicValue = dicValueService.getByType("lockState", item.getLockState());
            item.setLockState(dicValue.getText());
            return item;
        }).collect(Collectors.toList()));
        pageMap.put("total", pageInfo.getTotal());
        return pageMap;
    }

    @Override
    public List<User> list(User user) {
        return userDao.select(user);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 是否成功
     */
    @Override
    public boolean save(User user) {
        //判断当前新增用户，用户账号在数据库中是否存在
        User dbUser = userDao.selectByLoginAct(user.getLoginAct());
        if(dbUser != null){
            //抛出异常
            throw new CustomException(-2, "用户账户已存在");
        }

        //手动维护主键值 UUID
        user.setId(UUIDUtil.getUUID());
        //密码加密
        user.setLoginPwd(MD5Util.getMD5(user.getLoginPwd()));
        //填写新增和编辑时间
        user.setCreateTime(DateUtil.dateToString(new Date()));
        user.setEditTime(DateUtil.dateToString(new Date()));
        return userDao.insert(user) > 0;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 是否成功
     */
    @Override
    public boolean edit(User user) {
        //填写编辑时间
        user.setEditTime(DateUtil.dateToString(new Date()));
        return userDao.update(user) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean remove(String id) {
        return userDao.deleteById(id) > 0;
    }

    /**
     * 通过主键批量删除数据
     *
     * @param ids 主键
     * @return 是否成功
     */
    @Override
    public boolean removeBatch(String[] ids) {
        return userDao.deleteBatch(ids) > 0;
    }
}
