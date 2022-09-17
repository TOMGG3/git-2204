package com.bjpowernode.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjpowernode.dao.SysRoleMapper;
import com.bjpowernode.entity.SysRole;
import com.bjpowernode.service.SysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService{

    @Override
    public Map<String, Object> pageVo(Integer pageNumber, Integer pageSize, SysRole sysRole) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.hasLength(sysRole.getRolename()), "rolename", sysRole.getRolename());
        IPage<SysRole> page = this.page(new Page(pageNumber, pageSize), queryWrapper);
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("list", page.getRecords());
        pageMap.put("total", page.getTotal());
        return pageMap;
    }

    @Override
    public SysRole get(Integer id) {
        return this.baseMapper.selectVoById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(SysRole entity) {
        this.save(entity);
        if(!CollectionUtils.isEmpty(entity.getPermissionIdList())){
            this.baseMapper.insertRolePermission(entity);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean edit(SysRole entity) {
        this.updateById(entity);
        if(entity.getPermissionIdList() != null){
            this.baseMapper.deleteRolePermission(entity.getId());
            if(!entity.getPermissionIdList().isEmpty()){
                this.baseMapper.insertRolePermission(entity);
            }
        }
        return false;
    }

    @Override
    public List<SysRole> listByUserId(Integer userId) {
        return this.baseMapper.selectByUserId(userId);
    }
}




