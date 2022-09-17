package com.bjpowernode.security.utils;

import com.bjpowernode.security.pojo.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    /**
     * 获取当前登陆用户的Authentication对象
     */
    public static Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取自定义pojo类登陆用户对象
     */
    public static LoginUser getLoginUser(){
        return (LoginUser) getAuthentication().getPrincipal();
    }

    /**
     * 获取用户账号
     */
    public static String getUsername(){
        return getLoginUser().getUsername();
    }

    /**
     * 获取用户ID
     */
    public static Integer getUserId(){
        return getLoginUser().getId();
    }
}
