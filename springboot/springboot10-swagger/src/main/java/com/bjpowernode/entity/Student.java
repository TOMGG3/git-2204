package com.bjpowernode.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("学生实体")
public class Student {

    @ApiModelProperty("学生编号")
    private Integer id;
    @ApiModelProperty("学生姓名")
    private String name;
    @ApiModelProperty("学生生日")
    private Date birthday;
    @ApiModelProperty("学生地址")
    private String address;
}
