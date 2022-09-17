package com.bjpowernode.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjpowernode.entity.SysPermission;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface SysPermissionService extends IService<SysPermission> {

    Map<String, Object> pageVo(Integer pageNumber, Integer pageSize, SysPermission entity);

    List<SysPermission> tree();

    List<SysPermission> listByUserId(Integer userId);
}
