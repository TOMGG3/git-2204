package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RequestMappingController {

    /**
     * 注解的特殊用法：
     * 1.当配置项仅有一个，并且配置项为value，此时可以不用编写配置项名称
     * 2.当配置项的类型为数据类型，并且数据的元素仅有一个时，数组无需使用大括号包裹
     */
//    @RequestMapping("/url.do")
    @RequestMapping({"/url.do", "/getUrl.do", "/test.do"})
    public ModelAndView url(){
        System.out.println("测试@RequestMapping注解中的路径绑定...");
        return new ModelAndView("success");
    }

    /**
     * method = RequestMethod.GET
     * 表示限制当前请求的方式必须为get请求，其他请求一概不处理，响应客户端405状态码
     * 类似于Serlvet中doGet方法
     */
    @RequestMapping(value = "/doGet.do", method = RequestMethod.GET)
    public ModelAndView doGet(){
        System.out.println("限制当前请求必须为Get请求...");
        return new ModelAndView("success");
    }

    @RequestMapping(value = "/doPost.do", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView doPost(){
        System.out.println("限制当前请求必须为Post请求...");
        return new ModelAndView("success");
    }
}
