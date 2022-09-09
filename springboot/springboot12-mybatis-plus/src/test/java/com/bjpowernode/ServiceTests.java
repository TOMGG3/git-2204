package com.bjpowernode;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bjpowernode.entity.Emp;
import com.bjpowernode.service.EmpService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ServiceTests {

    @Autowired
    EmpService empService;

    @Test
    void testSave(){
        Emp e1 = new Emp(null, "aa", "aa", null, null, null, null, null);
        Emp e2 = new Emp(null, "bb", "bb", null, null, null, null, null);
        Emp e3 = new Emp(null, "cc", "cc", null, null, null, null, null);
        empService.saveBatch(Arrays.asList(e1, e2, e3));
    }

    @Test
    void testUpdate(){
        Emp e1 = new Emp(7945, "AA", "AA", null, null, null, null, null);
        Emp e2 = new Emp(7946, "BB", "BB", null, null, null, null, null);
        Emp e3 = new Emp(7947, "CC", "CC", null, null, null, null, null);
        empService.updateBatchById(Arrays.asList(e1, e2, e3));
    }

    @Test
    void testSaveOrUpdate(){
        Emp e1 = new Emp(7948, "ddd123", "ddd123", null, null, null, null, null);
        //主键值为null执行新增，不为null执行修改
        empService.saveOrUpdate(e1);
    }

    @Test
    void testGet(){
        Emp emp = empService.getById(7567);
    }

    @Test
    void testList(){
        List<Emp> list = empService.list();
    }

    @Test
    void testPage(){
        /**
         * IPage是MybatisPlus中提供一个分页工具类，类似于PageHelper中PageHelper以及PageInfo
         * Page是IPage接口的实现类
         */
        IPage<Emp> iPage = new Page<>(2, 5);
        IPage<Emp> result = empService.page(iPage);
        //当前页的列表数据
        List<Emp> list = result.getRecords();
        for (Emp emp : list) {
            System.out.println(emp);
        }
        System.out.println("当前页码：" + result.getCurrent());
        System.out.println("每页条数：" + result.getSize());
        System.out.println("总记录数：" + result.getTotal());
        System.out.println("总页数：" + result.getPages());
        System.out.println("是否存在上一页：" + ((Page<Emp>)result).hasPrevious());
        System.out.println("是否存在下一页：" + ((Page<Emp>)result).hasNext());
    }
}
