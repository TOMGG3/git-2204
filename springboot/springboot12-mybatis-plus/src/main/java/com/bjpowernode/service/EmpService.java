package com.bjpowernode.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjpowernode.entity.Emp;

/**
 * 在MybatisPlus中提供一个IService<实体类类型>的父接口
 * 在service接口中也可以扩展自己的方法
 */
public interface EmpService extends IService<Emp> {

    void test();
}
