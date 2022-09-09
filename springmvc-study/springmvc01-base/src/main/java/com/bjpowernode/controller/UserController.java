package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 处理请求之后跳转方式
     * 1.请求转发到页面
     * 2.重定向到页面
     * 3.请求转发到另一个处理器方法
     * 4.重定向到另一个处理器方法
     */
    @RequestMapping("/list.do")
    public String list(HttpServletRequest request){
        System.out.println("UserController中的list方法....");
//        request.setAttribute("message", "userList");
        System.out.println(request.getAttribute("message"));
        //通过视图名称，请求转发，默认
        return "success";
    }

    @RequestMapping("/save.do")
    public String save(HttpServletRequest request){
        System.out.println("UserController中的save方法....");
        request.setAttribute("message", "userSave");
        //重定向关键字 redirect:真实视图地址
        return "redirect:/pages/success.jsp";
    }

    @RequestMapping("/edit.do")
    public String edit(HttpServletRequest request){
        System.out.println("UserController中的edit方法....");
        request.setAttribute("message", "userEdit");
        //请求转发到另一个处理器方法：请求转发关键字 forward:真实处理器方法访问地址
        return "forward:/user/list.do";
    }

    @RequestMapping("/remove.do")
    public String remove(HttpServletRequest request){
        System.out.println("UserController中的remove方法....");
        request.setAttribute("message", "userRemove");
        return "redirect:/user/list.do";
    }
}
