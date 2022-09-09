package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
/**
 * @RequestMapping
 * 位置：类
 * 作用：在当前类中所有的处理请求的路径前添加前缀
 *
 * /list.do --> /student/list.do
 * /save.do --> /student/save.do
 * /edit.do --> /student/edit.do
 * /remove.do --> /student/remove.do
 */
@RequestMapping("/student")
public class StudentController {

    @RequestMapping("/list.do")
    public ModelAndView list(){
        System.out.println("StudentController中的list方法...");
        return new ModelAndView("success");
    }

    @RequestMapping("/save.do")
    public ModelAndView save(){
        System.out.println("StudentController中的save方法...");
        return new ModelAndView("success");
    }

    @RequestMapping("/edit.do")
    public ModelAndView edit(){
        System.out.println("StudentController中的edit方法...");
        return new ModelAndView("success");
    }

    @RequestMapping("/remove.do")
    public ModelAndView remove(){
        System.out.println("StudentController中的remove方法...");
        return new ModelAndView("success");
    }
}
