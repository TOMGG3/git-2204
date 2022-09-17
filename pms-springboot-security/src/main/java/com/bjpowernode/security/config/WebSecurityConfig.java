package com.bjpowernode.security.config;

import com.bjpowernode.security.filter.LoginAuthenticationFilter;
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
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 用户查询与处理器依赖注入
     */
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 配置用户信息
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * 配置http安全校验
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //开启跨域
                .cors()
                .and()
                //关闭跨站请求伪造防护
                .csrf().disable()
                //请求权限配置
                .authorizeHttpRequests()
                //配置url白名单（无需进行身份认证的url） permitAll()
                .antMatchers("/auth/login", "/webjars/**", "/druid/**", "/swagger-ui/**").permitAll()
                .anyRequest().authenticated()
                .and()
                //配置登陆相关，使用默认的过滤器
//                .formLogin().loginProcessingUrl("/auth/login").usernameParameter("username").passwordParameter("userpwd")
//                .successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler)
//                .and()
                //配置登出相关
                .logout().logoutUrl("/auth/logout").logoutSuccessHandler(logoutSuccessHandler)
                .and()
                //异常处理：无权限处理和未认证处理
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(authenticationEntryPoint)
                .and()
                //添加自定义的登陆过滤器
                .addFilter(loginAuthenticationFilter());
    }

    /**
     * 配置登陆过滤器
     */
    @Bean
    public LoginAuthenticationFilter loginAuthenticationFilter() throws Exception {
        LoginAuthenticationFilter loginAuthenticationFilter = new LoginAuthenticationFilter();
        loginAuthenticationFilter.setFilterProcessesUrl("/auth/login");
        loginAuthenticationFilter.setUsernameParameter("username");
        loginAuthenticationFilter.setPasswordParameter("userpwd");
        loginAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        loginAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        //设置身份认证管理器对象
        loginAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        return loginAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
