package com.bjpowernode.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjpowernode.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Entity com.bjpowernode.entity.SysUser
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 在单查用户信息的同时查询当前用户所拥有的角色ID
     */
    SysUser selectVoById(Integer id);

    /**
     * 添加用户对应角色
     */
    int insertUserRole(SysUser entity);

    /**
     * 删除用户对应角色（根据用户ID删除）
     */
    int deleteUserRole(Integer userId);
}




