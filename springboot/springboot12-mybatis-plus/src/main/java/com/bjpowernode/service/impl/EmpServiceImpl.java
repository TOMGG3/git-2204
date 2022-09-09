package com.bjpowernode.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjpowernode.dao.EmpDao;
import com.bjpowernode.entity.Emp;
import com.bjpowernode.service.EmpService;
import org.springframework.stereotype.Service;

/**
 * 在MybatisPlus中service实现类需要实现自定义接口，还需要继承一个父类ServiceImpl<持久层接口类型, 实体类类型>父类
 */
@Service
public class EmpServiceImpl extends ServiceImpl<EmpDao, Emp> implements EmpService {
    @Override
    public void test() {
        /**
         * 在自定义的service方法调用dao的方法
         * 通过this.baseMapper属性方法持久层，无需自己依赖注入
         * 也可调用自定义的持久层中的方法
         */
//        this.baseMapper.selectList(null);
    }
}
