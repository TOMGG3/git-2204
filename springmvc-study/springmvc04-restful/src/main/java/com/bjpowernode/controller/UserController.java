package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * @GetMapping = @RequestMapping + method = RequestMethod.GET
     */
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @GetMapping("/list")
    public String list(){
        System.out.println("UserController中list的方法....");
        return "success";
    }

    /**
     * @PathVariable
     * 位置：方法形参
     * 作用：路径传参时，将路径的数据获取到，并赋值给方法的形参
     */
    @GetMapping("/get/{id}")
    public String get(@PathVariable("id") Integer id){
        System.out.println("UserController中get的方法..." + id);
        return "success";
    }

    @PostMapping("/save")
    public String save(){
        System.out.println("UserController中save的方法....");
        return "success";
    }

    @PutMapping("/edit")
    public String edit(){
        System.out.println("UserController中edit的方法....");
        //put与delete请求必须重定向到jsp
        return "redirect:/pages/success.jsp";
    }

    @DeleteMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id){
        System.out.println("UserController中remove的方法...." + id);
        return "redirect:/pages/success.jsp";
    }
}
