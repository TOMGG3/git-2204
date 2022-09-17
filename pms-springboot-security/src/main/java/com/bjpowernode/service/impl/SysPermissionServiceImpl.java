package com.bjpowernode.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjpowernode.dao.SysPermissionMapper;
import com.bjpowernode.entity.SysPermission;
import com.bjpowernode.service.SysPermissionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService{

    @Override
    public Map<String, Object> pageVo(Integer pageNumber, Integer pageSize, SysPermission entity) {
        IPage<SysPermission> page = this.baseMapper.selectPageVo(new Page<>(pageNumber, pageSize), entity);
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("list", page.getRecords());
        pageMap.put("total", page.getTotal());
        return pageMap;
    }

    @Override
    public List<SysPermission> tree() {
        //源数据列表
        List<SysPermission> list = this.list();
        //树形数据列表
        List<SysPermission> tree = new ArrayList<>();
        //第一次遍历源数据
        for (SysPermission sysPermission : list) {
            //如果当前数据的父节点parentId为null或为0
            if(sysPermission.getParentId() == null || sysPermission.getParentId().equals(0)){
                //表示当前数据为根节点数据（没有上级）
                tree.add(sysPermission);
            }else{
                //表示当前节点不是根节点（有上级）
                //需要找到它上级，并且把它添加到上级的子数据集合中
                for (SysPermission permission : list) {
                    if(sysPermission.getParentId().equals(permission.getId())){
                        if(permission.getChildren() == null){
                            permission.setChildren(new ArrayList<>());
                        }
                        permission.getChildren().add(sysPermission);
                    }
                }
            }
        }
        return tree;
    }

    @Override
    public List<SysPermission> listByUserId(Integer userId) {
        return this.baseMapper.selectByUserId(userId);
    }
}




