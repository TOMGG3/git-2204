package com.bjpowernode.security.filter;

import com.bjpowernode.entity.SysUser;
import com.bjpowernode.security.utils.JsonUtil;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登陆过滤器
 * 要求：前端提交方式为post，并且数据格式为json字符串
 */
public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //判断请求方式是否为post
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("登陆方法不支持" + request.getMethod() + " 请求方式");
        }
        //判断数据是否json串
        if(!request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)){
            throw new AuthenticationServiceException("登陆数据必须为json格式");
        }
        //接收json数据
        SysUser sysUser = null;
        try {
            sysUser = JsonUtil.toBean(request.getInputStream(), SysUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(sysUser.getUsername(), sysUser.getUserpwd());
        this.setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);

    }
}
