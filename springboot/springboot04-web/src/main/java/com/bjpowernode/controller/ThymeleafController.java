package com.bjpowernode.controller;

import com.bjpowernode.pojo.Employee;
import com.bjpowernode.pojo.Point;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

@Controller
public class ThymeleafController {

    @GetMapping("/show")
    public String show(HttpServletRequest request){
        request.setAttribute("msg", "hello thymeleaf");
        request.setAttribute("message", "<h2>hello thymeleaf</h2>");

        return "success";
    }

    @GetMapping("/list")
    public String list(HttpServletRequest request){
        Employee e1 = new Employee(1, "张三", "职员", new Date(), "1");
        Employee e2 = new Employee(2, "李四", "经理", new Date(), "2");
        Employee e3 = new Employee(3, "王五", "会计", new Date(), "1");
        Employee e4 = new Employee(4, "汤姆", "程序员", new Date(), "2");
        Employee e5 = new Employee(5, "杰瑞", "职员", new Date(), "1");
        request.setAttribute("empList", Arrays.asList(e1, e2, e3,e4,e5));

        return "list";
    }

    @GetMapping("/get/{id}")
    public String get(@PathVariable("id") Integer id, HttpServletRequest request){
        Employee employee = null;
        switch (id){
            case 1:
                employee = new Employee(1, "张三", "职员", new Date(), "1");
                break;
            case 2:
                employee = new Employee(2, "李四", "经理", new Date(), "2");
                break;
            case 3:
                employee = new Employee(3, "王五", "会计", new Date(), "1");
                break;
            case 4:
                employee = new Employee(4, "汤姆", "程序员", new Date(), "2");
                break;
            case 5:
                employee = new Employee(5, "杰瑞", "职员", new Date(), "1");
                break;
        }
        request.setAttribute("emp", employee);
        return "edit";
    }

    @GetMapping("/getPoint")
    public String getPoint(Point point){
        System.out.println(point);
        return "success";
    }
}
