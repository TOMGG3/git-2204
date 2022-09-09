package com.bjpowernode.dao;

import com.bjpowernode.entity.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 代理配置mapper代理
 */
@Mapper
public interface EmpDao {

    List<Emp> select();

    Emp selectById(Integer id);

    int insert(Emp entity);

    int update(Emp entity);

    int deleteById(Integer id);
}
