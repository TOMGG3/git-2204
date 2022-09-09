package com.bjpowernode;

import com.bjpowernode.dao.UserDao;
import com.bjpowernode.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Springboot12MybatisPlusApplicationTests {

    @Autowired
    UserDao userDao;

    @Test
    void testSelectList() {
        List<User> userList = userDao.selectList(null);
        for (User user : userList) {
            System.out.println(user);
        }

    }

    @Test
    void testDelete(){
        userDao.deleteById("a39c0df0f7df3dce91ba1617c8ec388a");
    }

    @Test
    void testInsert(){
//        User user = new User(null, "小刘3", 23, "xiaoliu@qq.com",null);
//        int result = userDao.insert(user);
//        System.out.println(result);
    }

}
