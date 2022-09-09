package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("/hello.do")
    public String hello(){
        System.out.println("HelloController中的hello方法....");
        return "success";
    }
}
