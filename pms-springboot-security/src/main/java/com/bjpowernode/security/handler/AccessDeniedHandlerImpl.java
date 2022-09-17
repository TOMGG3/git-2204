package com.bjpowernode.security.handler;

import com.bjpowernode.security.utils.JsonUtil;
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
 * 鉴权失败处理器
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
        //设置内容类型
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        //将对象转换为json字符串
        response.getWriter().write(JsonUtil.toString(Result.error(-20, "您没有权限访问")));
    }
}
