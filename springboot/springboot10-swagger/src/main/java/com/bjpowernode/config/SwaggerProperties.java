package com.bjpowernode.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {

    /**
     * 扫描的包
     * 给这个包下面的接口创建文档
     */
    private String basePackage;
    /**
     * 作者姓名
     */
    private String name;
    /**
     * 作者主页链接
     */
    private String url;
    /**
     * 作者邮箱
     */
    private String email;
    /**
     * 版本号
     */
    private String version;
    /**
     * 分组名称
     */
    private String groupName;
    /**
     * 文档标题
     */
    private String title;
    /**
     * 文档描述
     */
    private String description;
    /**
     * 组织地址
     */
    private String termsOfServiceUrl;
    /**
     * 许可证
     */
    private String license;
    /**
     * 许可链接
     */
    private String licenseUrl;

}
