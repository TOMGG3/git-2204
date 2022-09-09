package com.bjpowernode.config;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Component
@ConfigurationProperties(prefix = "user")
//批量读取配置文件时的数据校验
@Validated
public class UserProperties {

    @NotBlank(message = "用户名不能为空")
    @Size(message = "用户名长度为6~15个字符", min = 6, max = 15)
    private String username;
    @NotNull(message = "年龄不能为空")
    @Range(message = "年龄范围为0~100岁", min = 0, max = 100)
    private Integer age;
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不合法")
    private String email;
}
