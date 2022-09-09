package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MenuController {

    @RequestMapping("/menu/find.do")
    public String find(){
        System.out.println("menu/find");
        return "redirect:/pages/system/menu.jsp";
    }
}
