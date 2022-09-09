package com.bjpowernode.config;

import com.bjpowernode.web.DemoFilter;
import com.bjpowernode.web.DemoListener;
import com.bjpowernode.web.DemoServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    /**
     * 注册Serlvet
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        //设置Servlet对象
        servletRegistrationBean.setServlet(new DemoServlet());
        //设置路径映射
        servletRegistrationBean.addUrlMappings("/demo");
        //设置初始化参数
        servletRegistrationBean.addInitParameter("msg", "hello");
//        servletRegistrationBean.setInitParameters();
        return servletRegistrationBean;
    }

    /**
     * 注册Filter
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new DemoFilter());
        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.addInitParameter();
        return filterRegistrationBean;
    }

    /**
     * 注册Listener
     */
    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean(){
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new DemoListener());
        return servletListenerRegistrationBean;
    }
}
