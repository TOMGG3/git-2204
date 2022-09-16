package com.bjpowernode.handler;

import com.bjpowernode.utils.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限不足（鉴权失败）处理器
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    /**
     * 鉴权失败之后的处理方法
     * @param request 原生请求对象
     * @param response 原生响应对象
     * @param accessDeniedException 权限鉴定失败异常
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //同步处理：页面跳转（请求转发，重定向）
        //异步处理：响应json字符串

        //设置内容类型
//        response.setContentType("application/json;charset=utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        //将对象转换为json字符串
        //ObjectMapper对象是jackson包下的一个工具类，将Java对象转换为json字符串，将json字符串转换为Java对象
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(Result.error(-20, "您没有权限访问")));
    }
}
