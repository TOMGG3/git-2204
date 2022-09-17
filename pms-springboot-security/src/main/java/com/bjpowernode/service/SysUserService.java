package com.bjpowernode.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjpowernode.entity.SysUser;

import java.util.Map;

/**
 *
 */
public interface SysUserService extends IService<SysUser> {

    Map<String, Object> pageVo(Integer pageNumber, Integer pageSize, SysUser sysUser);

    SysUser get(Integer id);

    boolean add(SysUser entity);

    boolean edit(SysUser entity);
}
