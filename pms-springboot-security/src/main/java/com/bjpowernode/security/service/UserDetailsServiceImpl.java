package com.bjpowernode.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bjpowernode.entity.SysPermission;
import com.bjpowernode.entity.SysRole;
import com.bjpowernode.entity.SysUser;
import com.bjpowernode.security.pojo.LoginUser;
import com.bjpowernode.service.SysPermissionService;
import com.bjpowernode.service.SysRoleService;
import com.bjpowernode.service.SysUserService;
import com.bjpowernode.utils.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysPermissionService sysPermissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户查询用户
        SysUser sysUser = sysUserService.getOne(new QueryWrapper<SysUser>().eq("username", username));
        if(sysUser == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        LoginUser loginUser = new LoginUser();
        //复制对象的属性值，条件两个对象中的属性名称必须相同
        BeanUtils.copyProperties(sysUser, loginUser);
        //查询当前登陆用户的权限
        //1.根据用户id查询角色数据
        List<SysRole> roleList = sysRoleService.listByUserId(sysUser.getId());
        //2.根据用户id查询权限数据
        List<SysPermission> permissionList = sysPermissionService.listByUserId(sysUser.getId());
        loginUser.setRoleNameList(roleList.stream().map(SysRole::getRolename).collect(Collectors.toList()));
        loginUser.setPercodeList(permissionList.stream().map(SysPermission::getPercode).collect(Collectors.toList()));
        return loginUser;
    }
}
