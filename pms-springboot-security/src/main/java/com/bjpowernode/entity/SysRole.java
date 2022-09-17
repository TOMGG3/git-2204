package com.bjpowernode.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色信息表
 * @TableName t_sys_role
 */
@TableName(value ="t_sys_role")
@Data
public class SysRole implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 角色名称
     */
    private String rolename;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除：0正常1删除
     */
    @TableLogic
    private String del;

    @TableField(exist = false)
    private List<Integer> permissionIdList;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
