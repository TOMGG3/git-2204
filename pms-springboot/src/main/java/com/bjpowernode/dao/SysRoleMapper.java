package com.bjpowernode.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjpowernode.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.bjpowernode.entity.SysRole
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 在单查角色信息的同时查询当前角色所拥有的权限ID
     */
    SysRole selectVoById(Integer id);

    /**
     * 添加角色对应权限
     */
    int insertRolePermission(SysRole entity);

    /**
     * 删除角色对应权限（根据角色ID删除）
     */
    int deleteRolePermission(Integer roleId);

    /**
     * 根据用户ID查询角色列表
     */
    List<SysRole> selectByUserId(Integer userId);
}




