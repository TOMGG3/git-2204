package com.bjpowernode.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

/**
 * hibernate validator是通过注解的方式实现数据校验
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    /**
     * @NotNull 数据不能为null
     * @NotEmpty 数据不能为null，也不能为空字符串
     * @NotBlank 数据不能为null，也不能为空字符串，不能为空白字符串
     *
     * 通用配置项：message，校验失败时提示信息
     */
//    @NotEmpty
    @NotBlank(message = "姓名不能为空")
    @Size(message = "姓名长度为2~8个字符", min = 2, max = 8)
    private String name;
    @NotNull(message = "年龄不能为空")
    @Range(message = "年龄范围为0~100岁", min = 0, max = 100)
    private Integer age;
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不合法")
//    @Pattern(message = "", regexp = "")
    private String email;
}
