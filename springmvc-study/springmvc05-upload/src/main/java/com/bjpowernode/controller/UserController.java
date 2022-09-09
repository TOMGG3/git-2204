package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/user/find.do")
    public String find(){
        System.out.println("user/find");
        return "forward:/role/find.do";
    }
}
