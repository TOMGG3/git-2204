package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class IndexController {

    @GetMapping("/dept/save")
    public String deptSave(){
        return "dept/save";
    }

    @GetMapping("/emp/list")
    public String empList(){
        return "emp/list";
    }
}
