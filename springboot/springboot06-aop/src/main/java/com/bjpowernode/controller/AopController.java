package com.bjpowernode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AopController {

    @GetMapping("/list")
    public String list(){
        System.out.println("AopController中的list方法....");
        return "ok";
    }
}
