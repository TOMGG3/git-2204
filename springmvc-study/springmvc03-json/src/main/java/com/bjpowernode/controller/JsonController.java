package com.bjpowernode.controller;

import com.bjpowernode.pojo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
//在类上使用，处理器类中所有方法都以json字符串的形式响应
@ResponseBody
public class JsonController {

    /**
     * 添加注解@ResponseBody
     * 作用：使当前方法的返回值不再去查找视图页面，而是直接将返回数据响应前端
     *      除了将字符串直接返回给前端之外，还可以将任何一种类型直接响应前端
     *      如果响应类型为String，那么就是字符串直接返回，如果类型是自定义类型或其他类型，会将对象转换为json字符串响应前端
     * 注意：如果需要响应json字符串，我们需要单独引入Java对象与json字符串转换工具，SpringMVC默认支持的转换工具为jackson
     */
    @RequestMapping("/getStr.do")
//    @ResponseBody
    public String getStr(HttpServletResponse response){
//        response.getWriter().write("ok");
        return "ok";
    }

    @RequestMapping("/getObj.do")
//    @ResponseBody
    public Student getObj(){
        Student student = new Student(10, "张三", new Date());
        return student;
    }

    @RequestMapping("/getList.do")
//    @ResponseBody
    public List<Student> getList(){
        Student s1 = new Student(10, "张三", new Date());
        Student s2 = new Student(11, "李四", new Date());
        Student s3 = new Student(12, "王五", new Date());
        return Arrays.asList(s1, s2, s3);
    }

    /**
     * 在后台使用@RequestBody来接收前端提交的json字符串格式的数据
     *
     * @RequestBody
     * 位置：方法的形参
     * 作用：将json字符串转换为Java对象
     *
     * 在SpringMVC提供json处理：
     * 1.响应json串 @ResponseBody
     * 2.接收json串 @RequestBody
     * 需要第三方依赖jackson工具
     */
    @RequestMapping("/sendData.do")
    public String sendData(@RequestBody Student student){
        System.out.println(student);
        return "ok";
    }
}
