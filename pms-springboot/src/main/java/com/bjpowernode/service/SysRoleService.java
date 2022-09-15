package com.bjpowernode.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjpowernode.entity.SysRole;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface SysRoleService extends IService<SysRole> {

    Map<String, Object> pageVo(Integer pageNumber, Integer pageSize, SysRole sysRole);

    SysRole get(Integer id);

    boolean add(SysRole entity);

    boolean edit(SysRole entity);

    List<SysRole> listByUserId(Integer userId);
}
