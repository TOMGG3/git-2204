package com.bjpowernode.controller;

import com.bjpowernode.bean.Emp;
import com.bjpowernode.config.MysqlProperties;
import com.bjpowernode.config.StudentProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ReadConfigController {

    /**
     * 配置的单个读取
     * @Value注解+SpringEL表达式来实现配置读取
     */
    @Value("${name}")
    private String username;
    @Value("${age}")
    private Integer age;
    @Value("${birthday}")
    private Date birthday;
    @Value("${gender}")
    private Boolean gender;
    @Value("${student.stu-name}")
    private String studentName;

    @GetMapping("/read")
    public String read(){
        return "姓名：" + username + " 年龄：" + age + " 生日：" + birthday + " 性别：" + gender + " 学生姓名：" + studentName;
    }

    @Autowired
    private StudentProperties studentProperties;

    @GetMapping("/readProperties")
    public StudentProperties readProperties(){
        return studentProperties;
    }

    @Autowired
    private MysqlProperties mysqlProperties;

    @GetMapping("/readProp")
    public MysqlProperties readProp(){
       return mysqlProperties;
   }

    @Autowired
    private Emp emp;

    @GetMapping("/getEmp")
    public Emp getEmp(){
        return emp;
    }
}
