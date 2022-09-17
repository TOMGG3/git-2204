package com.bjpowernode.security.handler;

import com.bjpowernode.security.pojo.LoginUser;
import com.bjpowernode.security.utils.JsonUtil;
import com.bjpowernode.security.utils.SecurityUtil;
import com.bjpowernode.utils.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆成功处理器
 */
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    /**
     *
     * @param request 请求
     * @param response 响应
     * @param authentication 当前登陆用户
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        LoginUser loginUser = SecurityUtil.getLoginUser();
        response.getWriter().write(JsonUtil.toString(Result.success(loginUser)));
    }
}
