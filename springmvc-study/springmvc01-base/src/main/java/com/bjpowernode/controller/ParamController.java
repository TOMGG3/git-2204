package com.bjpowernode.controller;

import com.bjpowernode.pojo.Person;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Indexed;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ParamController {

    /**
     * 在SpringMVC中接收请求参数使用的处理请求方法的形参
     * 自带形参
     * 1.HttpServletRequest 请求对象
     * 2.HttpServletResponse 响应对象
     * 3.HttpSession 会话对象
     * 4.Model: 由Spring提供的一个API接口，作用替代原生request域
     */
    @RequestMapping("/param.do")
    public String param(Model model){
        //向request域设置数据
        model.addAttribute("message", "model object");
        return "success";
    }

    /**
     * 接收简单类型
     *
     * SpringMVC通过处理请求方法的形参就可以直接接收请求参数，并且能够自动实现数据的类型转换
     * 默认日志格式：yyyy/MM/dd
     *
     * 要求：请求参数名与形参的名称必须保持一致
     *
     * @DateTimeFormat
     * 作用：将请求参数中日期字符串按照指定格式进行转换
     */
    @RequestMapping("/simple.do")
    public String simple(Integer id, String name, @DateTimeFormat(pattern = "yyyy-MM-dd") Date birth){
        System.out.println("id:" + id);
        System.out.println("name:" + name);
        System.out.println("birth:" + birth);
        return "success";
    }

    /**
     * @RequestParam注解
     * 1.手动绑定了请求参数与形参
     * 2.当形参标注了此注解后，在请求参数中必须有此请求参数，否则会报错
     *  通过required = false，可以不限制请求参数
     * 3.设置默认值
     */
    @RequestMapping("/simple2.do")
    public String simple2(@RequestParam(value = "num", required = false, defaultValue = "10") Integer id){
        System.out.println("id:" + id);
        return "success";
    }

    /**
     * 自定义类型的对象接收请求参数
     * 要求：请求参数名必须与类中的成员变量名称保持一致
     */
    @RequestMapping("/obj.do")
    public String obj(Person person, String name){
        System.out.println(person);
        System.out.println(name);
        return "success";
    }

    /**
     * 数组类型接收请求参数
     * 接收一个请求参数名对应多个请求参数值
     */
    @RequestMapping("/array.do")
    public String array(Integer[] ids){
        System.out.println(Arrays.toString(ids));
        return "success";
    }

    /**
     * List集合接收请求参数
     * 注意：如果使用list来接收数据,需要通过@RequestParam注解来标注
     */
    @RequestMapping("/list.do")
    public String list(@RequestParam List<Integer> ids){
        System.out.println("list集合：" + ids);
        return "success";
    }

    /**
     * Map集合接收请求参数
     * 与pojo对象接收请求参数用法一致，需要使用@RequestParam注解来标注
     *
     * 请求参数名作为map集合的key,请求参数值作为map集合的value
     */
    @RequestMapping("/map.do")
    public String map(@RequestParam Map<String, Object> map){
        System.out.println("Map集合:" + map);
        return "success";
    }

}
