package com.bjpowernode.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("用户名：" + username);
        /**
         * SpringSecurity提供一个UserDetails接口的实现类User
         * 模拟从数据库查询用户
         */
        return User.withUsername(username)
                .password(new BCryptPasswordEncoder().encode("123"))
                .authorities("sys:query", "sys:remove", "ROLE_ADMIN")
                .build();
    }
}
