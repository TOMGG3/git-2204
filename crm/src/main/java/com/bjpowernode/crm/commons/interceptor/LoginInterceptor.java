package com.bjpowernode.crm.commons.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆（身份认证）拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取session中的数据
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null){
            //没有登陆
            response.sendRedirect(request.getContextPath() + "/page/login");
            return false;
        }
        return true;
    }
}
