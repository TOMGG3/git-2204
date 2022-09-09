package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RoleController {

    @RequestMapping("/role/find.do")
    public String find(){
        System.out.println("role/find");
        return "redirect:/menu/find.do";
    }
}
