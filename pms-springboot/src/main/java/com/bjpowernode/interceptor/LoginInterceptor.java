package com.bjpowernode.interceptor;

import com.bjpowernode.utils.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 身份认证（登录）拦截器
 * @author steven
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //直接放行与异步请求相关OPTIONS请求
        if("OPTIONS".equalsIgnoreCase(request.getMethod())){
            return true;
        }
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null){
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(new ObjectMapper().writeValueAsString(Result.error(-5, "未登录")));
            return false;
        }
        return true;
    }
}
