package com.bjpowernode;

import com.bjpowernode.dao.EmpDao;
import com.bjpowernode.entity.Emp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class Springboot09MybatisApplicationTests {

    @Autowired
    EmpDao empDao;

    @Test
    void testSelectById() {
        Emp emp = empDao.selectById(7937);
        System.out.println(emp);
    }

    @Test
    void testSelect(){
        List<Emp> list = empDao.select();
        for (Emp emp : list) {
            System.out.println(emp);
        }
    }

    @Test
    void testPage(){
        PageHelper.startPage(1, 5);
        PageInfo<Emp> pageInfo = new PageInfo<>(empDao.select());
        List<Emp> list = pageInfo.getList();
        for (Emp emp : list) {
            System.out.println(emp);
        }
        System.out.println("总记录数：" + pageInfo.getTotal());
        System.out.println("总页数：" + pageInfo.getPages());
    }

    @Test
    void testInsert(){
        Emp emp = new Emp(null, "张三", "职员", 1, new Date(), 5800.0, 1000.0, 10);
        int result = empDao.insert(emp);
        System.out.println(result);
        System.out.println(emp.getEmpno());
    }

    @Test
    void testUpdate(){

        Emp emp = new Emp(7943, "李四", "经理", 1, new Date(), 5800.0, 1000.0, 10);
        int result = empDao.update(emp);
        System.out.println(result);
    }

    @Test
    void testDelete(){
        int result = empDao.deleteById(7934);
        System.out.println(result);
    }

}
