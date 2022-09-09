package com.bjpowernode.controller;

import com.bjpowernode.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/user/list")
    public List<User> list(){
        User u1 = new User(10, "admin", "123");
        User u2 = new User(11, "tom", "111");
        User u3 = new User(12, "jerry", "222");
        User u4 = new User(13, "chris", "333");

        return Arrays.asList(u1, u2, u3, u4);
    }
}
