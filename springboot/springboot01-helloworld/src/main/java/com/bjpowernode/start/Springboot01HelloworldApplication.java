package com.bjpowernode.start;

import com.bjpowernode.bean.Person;
import com.bjpowernode.bean.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * SpringBoot项目的启动类
 * @SpringBootApplication 启动注解
 *
 * @SpringBootConfiguration
 * 作用：表示当前类为一个配置类（XML）
 *      启动类也是一个配置类
 *
 * @EnableAutoConfiguration
 * 作用：开启自动化配置
 *      1.配置扫描基础包
 *      @AutoConfigurationPackage --> @Import({Registrar.class}) --> registerBeanDefinitions()
 *      启动类所在包就是默认扫描基础包
 *      自定义基础扫描包
 *      @ComponentScan(basePackages="com.bjpowernode")
 *      @SpringBootApplication(scanBasePackages = "com.bjpowernode") 推荐使用
 *      2.自动化配置
 *      @Import({AutoConfigurationImportSelector.class})  --> AutoConfigurationImportSelector --> getAutoConfigurationEntry()
 *      SpringBoot程序在启动时会加载133个自动化配置类（相当于以前xml配置），其中最终生效为部分自动化配置类24个
 *
 *      @Conditional以及@ConditionalXXX 配置生效条件注解
 *      @ConditionalOnWebApplication 当前项目必须为Web项目
 *      @ConditionalOnClass 指定类必须在当前项目中存在，如果不存在则配置不生效
 *      @ConditionalOnMissingClass 指定类必须在当前项目中不存在，如果存在则配置不生效
 *      @ConditionalOnBean 指定的bean对象必须在当前项目的IOC容器中存在，如果不存在则配置不生效
 *      @ConditionalOnMissingBean 指定的bean对象必须在当前项目的IOC容器中不存在，如果存在则配置不生效
 *
 * @ComponentScan
 * 作用：配置基础扫描包，相当于applicationContext.xml文件中的<context:componment-scan>
 */
@SpringBootApplication(scanBasePackages = "com.bjpowernode")
//@ComponentScan(basePackages="com.bjpowernode")
public class Springboot01HelloworldApplication {

    public static void main(String[] args) {
        //run()方法返回值为ioc容器对象
        ConfigurableApplicationContext ioc = SpringApplication.run(Springboot01HelloworldApplication.class, args);

//        Student student = ioc.getBean("stu", Student.class);
//        System.out.println(student);
//
//        Person person = ioc.getBean(Person.class);
//        System.out.println(person);
    }

}
