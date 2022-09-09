package com.bjpowernode.controller;

import com.bjpowernode.pojo.Student;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    /**
     * 开启校验 @Validated
     * 接收校验失败信息：需要在接收请求参数的形参后定义一个BindingResult类型的形参
     * BindingResult记录校验失败信息
     */
    @PostMapping("/student/save")
    public String save(@Validated Student student, BindingResult bindingResult){
        //判断校验是否通过
        if(bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError error : allErrors) {
                System.out.println(error.getDefaultMessage());
            }
        }else{
            System.out.println(student);
        }

        return "ok";
    }
}
