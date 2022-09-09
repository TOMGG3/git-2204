package com.bjpowernode.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 批量读取配置类
 * 1.成员变量的名称必须与配置的key保持一致（中划线自动转驼峰）
 * 2.注解
 *     1）Componment + @ConfigurationProperties
 *     2）@EnableConfigurationProperties + @ConfigurationProperties
 *
 * @ConfigurationProperties
 * 批量读取配置文件的注解
 * 配置项：prefix,读取前缀
 */
//@Component
@ConfigurationProperties(prefix = "student")
public class StudentProperties {

    private String stuName;
    private Integer age;
    private Date birth;
    private String[] hobby;
    private AddressProperties address;

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String[] getHobby() {
        return hobby;
    }

    public void setHobby(String[] hobby) {
        this.hobby = hobby;
    }

    public AddressProperties getAddress() {
        return address;
    }

    public void setAddress(AddressProperties address) {
        this.address = address;
    }
}
