package com.bjpowernode;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bjpowernode.entity.Emp;
import com.bjpowernode.service.EmpService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

@SpringBootTest
public class WrapperTests {

    @Autowired
    EmpService empService;

    @Test
    void testList(){
        Integer deptno = 10;
        Double sal = 1500.0;
        String ename = "S";
        QueryWrapper<Emp> queryWrapper = new QueryWrapper();
        /**
         * 条件构造器
         * eq()相等
         * ne()不相等
         * gt()大于
         * ge()大于等于
         * lt()小于
         * le()小于等于
         */
        queryWrapper.eq(deptno!=null, "deptno", deptno);
        queryWrapper.ge(sal!=null, "sal", sal);
        queryWrapper.like(StringUtils.hasLength(ename), "ename", ename);
        queryWrapper.orderBy(true, false, "deptno", "sal");
        queryWrapper.select("empno", "ename", "job");
        empService.list(queryWrapper);
    }

    @Test
    void testUpdate(){
        Emp emp = new Emp();
        emp.setSal(3000.0);
        UpdateWrapper<Emp> updateWrapper = new UpdateWrapper<>();
        updateWrapper.isNull("sal");
        empService.update(emp, updateWrapper);
    }

    @Test
    void testRemove(){
        UpdateWrapper<Emp> updateWrapper = new UpdateWrapper<>();
        updateWrapper.isNull("deptno");
        empService.remove(updateWrapper);
    }
}
