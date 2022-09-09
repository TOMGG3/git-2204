package com.bjpowernode;

import com.bjpowernode.dao.EmpDao;
import com.bjpowernode.entity.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class MapperTests {

    @Autowired
    EmpDao empDao;

    @Test
    void testInsert(){
        //MybatisPlus主键返回功能默认开启
        Emp emp = new Emp(null, "小王", "职员", 1, new Date(), 6500.0, 1500.0, 10);
        System.out.println(emp.getEmpno());
        int result = empDao.insert(emp);
        System.out.println(emp.getEmpno());
        System.out.println(result);
    }

    @Test
    void testDelete(){
//        empDao.deleteById(7937);
//        empDao.deleteBatchIds(Arrays.asList(1,2,3,4));
        Map<String, Object> map = new HashMap<>();
        map.put("deptno", 100);
        empDao.deleteByMap(map);
    }

    @Test
    void testUpdate(){
//        Emp emp = new Emp(7942, "张飞", "会计", 2, new Date(), 7000.0, 1500.0, 20);
        Emp emp = new Emp(7942, null, "程序员", null, null, 12000.0, 3000.0, null);
        empDao.updateById(emp);
    }

    @Test
    void testSelectById(){
        Emp emp = empDao.selectById(7369);
        System.out.println(emp);
    }

    @Test
    void testSelectList(){
        List<Emp> list = empDao.selectBatchIds(Arrays.asList(1,2,3,4));
        List<Emp> list2 = empDao.selectList(null);
        Map<String, Object> map = new HashMap<>();
        map.put("sal", 1250.0);
        List<Emp> list3 = empDao.selectByMap(map);
        List<Map<String, Object>> list4 = empDao.selectMaps(null);
        System.out.println(list4);
    }
}
