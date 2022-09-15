package com.bjpowernode.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bjpowernode.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.bjpowernode.entity.SysPermission
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 在查询权限的同时，关联查询权限的父权限
     * @param page 分页条件对象
     * @param entity 条件查询对象
     * @return
     */
    IPage<SysPermission> selectPageVo(IPage<SysPermission> page, @Param("entity") SysPermission entity);

    /**
     * 根据用户ID查询权限列表
     */
    List<SysPermission> selectByUserId(Integer userId);
}




