package com.bjpowernode.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bjpowernode.entity.SysUser;
import com.bjpowernode.service.SysUserService;
import com.bjpowernode.utils.MD5Util;
import com.bjpowernode.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 登陆与登出
 * 基于HttpSession
 * @author steven
 */
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/login")
    public Result login(@RequestBody SysUser sysUser, HttpSession session){
        SysUser dbUser = sysUserService.getOne(new QueryWrapper<SysUser>().eq("username", sysUser.getUsername()));
        if(dbUser == null){
            return Result.error(-2, "用户名有误");
        }
        if(!dbUser.getUserpwd().equals(MD5Util.getMD5(sysUser.getUserpwd()))){
            return Result.error(-3, "密码有误");
        }
        if("2".equals(dbUser.getStatus())){
            return Result.error(-4, "当前用户被禁用，请联系管理员");
        }
        session.setAttribute("loginUser", dbUser);
        dbUser.setUserpwd("");
        return Result.success(dbUser);
    }

    @GetMapping("/logout")
    public Result logout(HttpSession session){
        session.removeAttribute("loginUser");
        session.invalidate();
        return Result.success();
    }
}
