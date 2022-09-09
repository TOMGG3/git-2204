package com.bjpowernode.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bjpowernode.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 在MybatisPlus中dao接口无需定义任意抽象方法，需要继承一个父接口BaseMapper<实体类类型>，基础的CURD的抽象方法都有了
 *
 * 在dao接口中也可以扩展自己的方法,需要使用mapper文件编写sql语句
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
}
