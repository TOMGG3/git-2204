package com.bjpowernode.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class GetUserInfoController {

    /**
     * 当前登陆的用户信息对象Principal
     */
    @GetMapping("/userInfo")
    public Principal userInfo(Principal principal){
        return principal;
    }

    @GetMapping("/getUserInfo")
    public Object getUserInfo(){
        /**
         * SecurityContext 框架的上下文对象，类似于JavaWeb中ServletContext
         */
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        return authentication;
    }
}
