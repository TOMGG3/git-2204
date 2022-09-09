package com.bjpowernode;

import com.bjpowernode.config.AddressProperties;
import com.bjpowernode.config.StudentProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableConfigurationProperties({StudentProperties.class, AddressProperties.class})
//读取原生spring的配置xml文件
@ImportResource(locations = "classpath:applicationContext.xml")
public class Springboot02ConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot02ConfigApplication.class, args);
    }

}
