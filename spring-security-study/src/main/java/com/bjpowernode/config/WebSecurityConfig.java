package com.bjpowernode.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * SpringSecurity的配置
 * 需要继承父类WebSecurityConfigurerAdapter
 */
@Configuration
//开启注释式鉴权: prePostEnabled开启方法的前置与后置鉴权
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 注入处理器
     */
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
//    @Autowired
//    private AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    /**
     * 注入查询用户的服务对象
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 配置Security登陆用户的信息
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //配置自定义的UserDetailsService实现来获取用户信息
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        //配置模拟数据：在内存中配置用户信息
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password(passwordEncoder().encode("123"))
//                //Authority在框架分为两种：角色，权限
//                //配置角色
//                //如果为用户配置角色，那么系统会在角色的名称字符串前追加一个ROLE_前缀，表示当前权限为角色型权限
//                .roles("ADMIN","MANAGER","STUDENT")
//                //配置权限
//                //权限字符串，模块名称:操作名称
//                .authorities("sys:query", "sys:save", "sys:update", "sys:remove")
//                .and()
//                .withUser("tom")
//                .password(passwordEncoder().encode("111"))
//                .roles("MANAGER")
//                .authorities("sys:query", "sys:save")
//                .and()
//                .withUser("jerry")
//                .password(passwordEncoder().encode("222"))
//                .roles("STUDENT");
    }

    /**
     * 配置路径与权限关系（鉴权）
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //此方法中参数HttpSecurity必须调用formLogin()方法，否则原生登陆会失效。
        http.formLogin()
                //同步请求处理
//                .successForwardUrl("登陆成功跳转页面路径")
//                .failureUrl("登陆失败跳转页面路径")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);
        //配置鉴权失败处理器
        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
        /**
         * hasRole()判断当前登陆用户是否有指定的角色
         * hasAnyRole() 判断当前登陆用户是否拥有指定多个角色中的一个
         * hasAuthority()判断当前登陆用户是否有指定的权限
         * hasAnyAuthority() 判断当前登陆用户是否拥有指定多个权限中的一个
         */
        http.authorizeHttpRequests()
                //编程式权限鉴定
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/manager/save").hasRole("MANAGER")
//                .antMatchers("/student/remove").hasRole("STUDENT")
//                .antMatchers("/sys/query").hasAuthority("sys:query")
//                .antMatchers("/sys/save").hasAuthority("sys:save")
//                .antMatchers("/sys/update").hasAuthority("sys:update")
//                .antMatchers("/sys/remove").hasAuthority("sys:remove")
                //所有的请求都必须进行身份认证
                .anyRequest().authenticated();
    }

    /**
     * SpringSecurity5.0开始，强制要求密码加密
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
