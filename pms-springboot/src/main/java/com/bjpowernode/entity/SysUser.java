package com.bjpowernode.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户信息表
 * @TableName t_sys_user
 */
@TableName(value ="t_sys_user")
@Data
public class SysUser implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户性别（0男 1女 2保密）
     */
    private String gender;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String userpwd;

    /**
     * 用户类型：1用户2学生
     */
    private String userType;

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
     * 状态：1可用2不可用
     */
    private String status;

    /**
     * 是否删除：0正常1删除
     */
    @TableLogic
    private String del;

    /**
     * 当前用户拥有的角色id列表
     */
    @TableField(exist = false)
    private List<Integer> roleIdList;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
