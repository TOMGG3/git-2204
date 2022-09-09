package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    @RequestMapping("/list.do")
    public String list(){
        System.out.println("StudentController中的list方法....");
        return "success";
    }

    @RequestMapping("/save.do")
    public String save(){
        System.out.println("StudentController中的save方法....");
        return "success";
    }

    @RequestMapping("/edit.do")
    public String edit(){
        System.out.println("StudentController中的edit方法....");
        return "success";
    }

    @RequestMapping("/remove.do")
    public String remove(){
        System.out.println("StudentController中的remove方法....");
        return "success";
    }
}
