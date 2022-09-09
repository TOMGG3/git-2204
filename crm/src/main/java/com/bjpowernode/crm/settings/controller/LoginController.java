package com.bjpowernode.crm.settings.controller;

import com.bjpowernode.crm.commons.utils.DateUtil;
import com.bjpowernode.crm.commons.utils.MD5Util;
import com.bjpowernode.crm.commons.utils.Result;
import com.bjpowernode.crm.settings.entity.User;
import com.bjpowernode.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(String loginAct, String loginPwd, HttpSession session){
        //根据用户名查询用户是否存在
        User loginUser = userService.getByLoginAct(loginAct);
        //判断用户名是否正确
        if(loginUser == null){
            //1.抛出异常
            //2.响应错误对象
            return Result.error(-2, "登陆用户不存在");
        }
        //判断密码是否正确
        if(!MD5Util.getMD5(loginPwd).equals(loginUser.getLoginPwd())){
            return Result.error(-2, "登陆密码有误");
        }
        //判断用户是否被锁定
        if("0".equals(loginUser.getLockState())){
            return Result.error(-2, "当前账号已被锁定，请联系管理员");
        }
        //判断用户失效时间
        String expireTime = loginUser.getExpireTime();
        if(StringUtils.hasLength(expireTime) && new Date().compareTo(DateUtil.stringToDate(expireTime)) > 0){
            return Result.error(-2, "当前账号已失效，请联系管理员");
        }
        //判断都通过了，表示登陆成功
        session.setAttribute("loginUser", loginUser);
        return Result.success();
    }

    @GetMapping("/logout")
    public Result logout(HttpSession session){
        session.removeAttribute("loginUser");
        session.invalidate();
        return Result.success();
    }
}
