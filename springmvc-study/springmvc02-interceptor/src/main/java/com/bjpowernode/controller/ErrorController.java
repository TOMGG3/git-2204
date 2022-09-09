package com.bjpowernode.controller;

import com.bjpowernode.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {

    /**
     * 浏览器 <--> DispatcherServlet <--> Controller(处理器) <--> Service(业务) <--> Dao(持久层) <--> 数据库(mysql)
     */

    @RequestMapping("/getError.do")
    public String getError(@RequestParam(value = "num", defaultValue = "1") Integer num) throws Exception {
        //异常模拟
        if(num < 0){
            //编译期异常
            Class.forName("abc");
        }else if(num == 0){
            //运行时异常
            System.out.println(10/num);
        }else if(num >= 1 && num <= 10){
            throw new MyException("哈哈");
        }
        return "success";
    }

    /**
     * 局部异常处理器：仅能处理所在处理器方法抛出的异常
     *
     * 处理异常的方法
     * 当前处理器方法抛出异常之后，会进入此方法，通过注解的方式
     *
     * @ExceptionHandler
     * 作用：使当前方法成一个处理异常的方法
     * 配置项value: 表示当前方法处理的异常类型
     */
//    @ExceptionHandler(Exception.class)
    public String handleException(HttpServletRequest request, Exception e){
        request.setAttribute("message", "有异常发生..." + e.getMessage());
        return "error";
    }
}
