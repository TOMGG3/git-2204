package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class LoginController {

    @PostMapping("/login")
    public String login(String username, String password, HttpSession session, HttpServletRequest request){
        if(("admin".equals(username) || "root".equals(username)) && "123".equals(password)){
            //登陆成功
            session.setAttribute("loginUser", username);
            return "index";
        }
        //登陆失败
        request.setAttribute("message", "用户名或密码有误");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginUser");
        session.invalidate();
        return "login";
    }
}
