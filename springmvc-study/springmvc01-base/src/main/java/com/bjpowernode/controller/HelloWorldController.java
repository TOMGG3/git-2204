package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * SpringMVC中第五大组件：处理器
 * 类名自定义，通常情况下以Controller结尾，无需继承或实现任何的父类或父接口
 * 以注解的方式标注@Controller
 *
 */
@Controller
public class HelloWorldController {

    /**
     * 处理请求的方法
     * 名称：自定义
     * 返回值类型：ModelAndView
     * 参数：接收请求参数
     * 注解：@RequestMapping,作用：将一个请求的路径与当前处理请求的方法进行绑定
     */
    @RequestMapping("/hello.do")
    public ModelAndView hello(){
        System.out.println("HelloWorldController中的hello方法执行了...");
        ModelAndView modelAndView = new ModelAndView();
        //设置数据: springmvc会将数据设置到request域对象中
        modelAndView.addObject("message", "hello springmvc");
        //设置视图：设置页面跳转路径
        //  /pages/ pages/success.jsp   .jsp
        // modelAndView.setViewName("/pages/success.jsp");
        /**
         * 逻辑视图名：真实页面路径的一部分
         * 真实路径 = 视图解析中的前缀 + 逻辑视图名 + 后缀
         */
        modelAndView.setViewName("success");
        return modelAndView;
    }

}
