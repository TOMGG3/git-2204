package com.bjpowernode.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 权限信息表
 * @TableName t_sys_permission
 */
@TableName(value ="t_sys_permission")
@Data
public class SysPermission implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源类型：D目录 M菜单 B按钮
     */
    private String type;

    /**
     * 访问地址
     */
    private String url;

    /**
     * 字体图标
     */
    private String icon;

    /**
     * 权限代码
     */
    private String percode;

    /**
     * 父节点ID
     */
    private Integer parentId;

    /**
     * 父权限名称
     */
    @TableField(exist = false)
    private String parentName;

    /**
     * 显示顺序
     */
    private Integer sort;

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

    /**
     * 数据子数据集合
     */
    @TableField(exist = false)
    private List<SysPermission> children;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
