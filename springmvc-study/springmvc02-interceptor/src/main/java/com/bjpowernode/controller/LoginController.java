package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping("/login.do")
    public String login(String username, String password, HttpSession session, HttpServletRequest request){
        //模拟登陆
        if("admin".equals(username) && "123".equals(password)){
            session.setAttribute("loginUser", username);
            return "index";
        }
        request.setAttribute("message", "用户名或密码有误");
        return "login";
    }

    @RequestMapping("/logout.do")
    public String logout(HttpSession session){
        session.removeAttribute("loginUser");
        //注销session
        session.invalidate();
        return "login";
    }
}
