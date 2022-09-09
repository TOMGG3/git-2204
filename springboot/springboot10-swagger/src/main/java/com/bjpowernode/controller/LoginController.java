package com.bjpowernode.controller;

import com.bjpowernode.entity.LoginUser;
import com.bjpowernode.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Api(tags = "用户登陆接口")
public class LoginController {

    @PostMapping("/login")
    @ApiOperation("用户登陆方法")
    public Result login(LoginUser loginUser){
        System.out.println(loginUser);
        return Result.success();
    }
}
