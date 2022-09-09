package com.bjpowernode.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 * 实现HandlerInterceptor接口
 *
 * 三个方法执行顺序
 * 1.pre demo1,demo2
 * 2.controler
 * 3.post demo2,demo1
 * 4.after demo2,demo1
 *
 * postHandle与afterCompletion的区别
 * 请求能够进入Controller中postHandle方法才会执行，如果请求无法进入Controller中，postHandle是不会执行。
 * afterCompletion是否能够执行关键点不是看请求是否能够进入Controller中，而是看当前拦截器是否放行，
 * 如果拦截器放行了，即使在整个拦截器链中请求被拦截器，afterCompletion也会执行。
 */
public class Demo1Interceptor implements HandlerInterceptor {

    /**
     * preHandle预处理方法，前置方法，在执行Controller方法之前执行
     * @param request 原生request对象
     * @param response 原生response对象
     * @param handler 处理器Controller对象
     * @return 布尔类型：true表示放行（调用后续的拦截器或Controller方法），false表示拦截，请求不再向后执行
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Demo1Interceptor拦截器中的preHandle方法执行了....");
        return true;
    }

    /**
     * postHandle 后处理方法，在请求进入Controller方法之后执行
     * @param request 原生request对象
     * @param response 原生response对象
     * @param handler 处理器Controller对象
     * @param modelAndView Controller方法返回值对象
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Demo1Interceptor拦截器中的postHandle方法执行了....");
    }

    /**
     * afterCompletion 拦截器的后处理方法
     * @param request 原生request对象
     * @param response 原生response对象
     * @param handler 处理器Controller对象
     * @param ex 处理器方法在执行过程中抛出异常对象
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("Demo1Interceptor拦截器中的afterCompletion方法执行了....");
    }
}
