package com.bjpowernode.config;

import com.bjpowernode.bean.Person;
import com.bjpowernode.bean.Student;
import com.bjpowernode.bean.Teacher;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 配置类
 * @Configuration表示当前类为spring中的一个配置类，相当于applicationContext.xml
 */
@Configuration
//当前项目必须有Teacher类
@ConditionalOnClass(Teacher.class)
//当前项目中必须有Teacher类型的Bean
@ConditionalOnBean(Teacher.class)
/**
 * 快速导入：配置bean
 * @Import: 仅能使用在配置类上或springboot项目的启动类上
 */
//@Import(Person.class)
public class StudentConfig {

    /**
     * @Bean
     * 作用：配置bean，相当于<bean>标签
     * 方法的返回值类型为bean标签的class属性
     * 方法的名称为bean标签的id属性
     */
    @Bean("stu")
    public Student getStudent(){
        return new Student();
    }
}
