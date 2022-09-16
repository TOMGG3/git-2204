package com.bjpowernode;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderTests {

    @Test
    void run(){
        String source = "123";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String p1 = passwordEncoder.encode(source);
        System.out.println(p1);
        String p2 = passwordEncoder.encode(source);
        System.out.println(p2);
        String p3 = passwordEncoder.encode(source);
        System.out.println(p3);

        //密码加密对比
        System.out.println(passwordEncoder.matches("123", p1));
        System.out.println(passwordEncoder.matches("123", p2));
        System.out.println(passwordEncoder.matches("123", p3));
    }
}
