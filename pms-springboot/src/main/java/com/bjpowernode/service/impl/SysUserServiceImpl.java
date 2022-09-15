package com.bjpowernode.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjpowernode.dao.SysUserMapper;
import com.bjpowernode.entity.SysUser;
import com.bjpowernode.service.SysUserService;
import com.bjpowernode.utils.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService{

    @Override
    public Map<String, Object> pageVo(Integer pageNumber, Integer pageSize, SysUser sysUser) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.hasLength(sysUser.getUsername()), "username", sysUser.getUsername());
        queryWrapper.like(StringUtils.hasLength(sysUser.getNickname()), "nickname", sysUser.getNickname());
        queryWrapper.eq(StringUtils.hasLength(sysUser.getUserType()), "user_type", sysUser.getUserType());
        IPage<SysUser> page = this.page(new Page(pageNumber, pageSize), queryWrapper);
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("list", page.getRecords());
        pageMap.put("total", page.getTotal());
        return pageMap;
    }

    @Override
    public SysUser get(Integer id) {
        return this.baseMapper.selectVoById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(SysUser entity) {
        entity.setUserpwd(MD5Util.getMD5("123456"));
        this.save(entity);
        if(!CollectionUtils.isEmpty(entity.getRoleIdList())){
            this.baseMapper.insertUserRole(entity);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean edit(SysUser entity) {
        this.updateById(entity);
        if(entity.getRoleIdList() != null){
            this.baseMapper.deleteUserRole(entity.getId());
            if(!entity.getRoleIdList().isEmpty()){
                this.baseMapper.insertUserRole(entity);
            }
        }
        return true;
    }
}
