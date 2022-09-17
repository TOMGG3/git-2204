package com.bjpowernode.security.handler;

import com.bjpowernode.security.utils.JsonUtil;
import com.bjpowernode.utils.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆失败处理器
 */
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    /**
     *
     * @param request 请求
     * @param response 响应
     * @param exception 身份认证异常
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        String message = "";
        if(exception instanceof UsernameNotFoundException){
            message = "用户名有误";
        }else if(exception instanceof BadCredentialsException){
            message = "用户名或密码有误";
        }else if(exception instanceof LockedException){
            message = "当前用户被锁定，请联系管理员";
        }else if(exception instanceof DisabledException){
            message = "当前用户被禁用，请联系管理员";
        }else if(exception instanceof AccountExpiredException){
            message = "账号已过期，请联系管理员";
        }else if(exception instanceof CredentialsExpiredException){
            message = "密码已过期，请联系管理员";
        }

        response.getWriter().write(JsonUtil.toString(Result.error(-10, message)));
    }
}
