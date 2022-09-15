package com.bjpowernode.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author steven
 * 同源策略：
 * 异步请求在发送服务时，浏览器会检查服务端的“访问协议”、“IP地址”、“端口号”是否与当前服务保持一致
 * http与https不是同一个协议
 * localhost与127.0.0.1不是一个IP
 *
 * 通过设置响应头的方式配置跨域处理
 */
@Configuration
public class CorsConfig /*implements WebMvcConfigurer*/ {

    /**
     * 设置全局跨域处理
     * @param registry
     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
                //设置允许跨域访问的服务器地址, *表示所有
//                .allowedOrigins("http://localhost:8081")
//                .allowedOrigins("*")
//                .allowedOriginPatterns("*")
                //设置允许跨域访问的http协议方法, *表示所有
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                //设置跨域时是否允许携带凭证（JSESSIONID）
//                .allowCredentials(true)
//                .allowedHeaders("Content-Type", "X-Requested-With", "Access-Control-Request-Method",
//                        "Access-Control-Request-Headers", "accept", "Origin")
//                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                //其他服务器在发出跨域请求之前先发送一个请求询问当前服务器是否允许跨域访问
//                .maxAge(3600);
//    }

    /**
     * 通过过滤器的方式配置跨域处理
     */
    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //设置访问源地址
        corsConfiguration.addAllowedOriginPattern("*");
        //设置访问源请求方法
        corsConfiguration.addAllowedMethod("*");
        //设置访问源请求头
        corsConfiguration.addAllowedHeader("*");
        //设置访问源是否允许携带凭证
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        //对接口配置跨域设置
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
}
