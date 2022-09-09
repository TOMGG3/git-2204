package com.bjpowernode.config;

import com.bjpowernode.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.*;

/**
 * SpringMVC的配置类
 *
 * 需要实现一个接口
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    /**
     * 动态页面映射
     * 动态页面(逻辑视图名)与一个url进行绑定
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/page/login").setViewName("login");
        //修改欢迎页面
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/page/index").setViewName("index");
    }

    /**
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry
//                //配置拦截器对象
//                .addInterceptor(new LoginInterceptor())
//                //配置拦截器的拦截路径
//                .addPathPatterns("/**")
//                //配置放行路径
//                .excludePathPatterns("/auth/login","/static/**", "/webjars/**", "/page/login", "/");
//        registry.addInterceptor().addPathPatterns().excludePathPatterns();
    }

    /**
     * 静态资源处理
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static2/**")
//                .addResourceLocations("classpath:/my2/");
    }

    /**
     * 添加自定义数据类型格式转换器
     *
     * 自带日期类型转换器
     * @DateTimeFormat() String --> Date
     * @JsonFormat() Date --> json String
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
//        registry.addFormatter(Formatter接口的实现类对象);
        registry.addConverter(new PointConverter());
    }

    /**
     * 配置跨域
     *
     * 同源策略：
     * 发送异步请求时，请求地址中的协议、服务器地址、服务器的端口号必须保持一致
     * 如果不一致，就违反了浏览器的同源策略，浏览器会禁止访问，在页面会收到一个CORS的错误
     *
     * http://localhost:8080
     *
     * 注意：
     * 协议http与https不同源
     * 地址localhost与127.0.0.1不同源
     *
     * 前后端分离的项目：设置全局跨域处理 @CrossOrigin
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                //跨域访问的资源路径
                .addMapping("/**")
                //设置允许跨域访问的服务器地址
//                .allowedOriginPatterns("http://localhost:64679")
                .allowedOriginPatterns("*")
                //设置允许跨域访问的请求方式
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedMethods("*")
                //设置允许跨域访问时的头部信息
//                .allowedHeaders("*")
                //设置暴露给客户端的头部信息
//                .exposedHeaders("*")
                //设置跨域时是否允许携带凭证信息（cookie中JSESSIONID）
                .allowCredentials(true)
                //跨域访问时先发送一个询问请求（询问当前服务是否能够跨域）
                //设置询问请求发送周期
                .maxAge(3600);
    }
}
