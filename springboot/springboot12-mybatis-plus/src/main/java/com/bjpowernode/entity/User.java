package com.bjpowernode.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//描述实体与表的对应关系
@TableName("tbl_user")
public class User {

    /**
     * 实体类中的属性与主键字段对应关系的描述
     * @TableId
     * 配置项：
     * value对应字段名称
     * type主键生成策略
     * IdType.AUTO 不生成主键值，靠数据库生成（数据库必须支持表内自增），类型要求：int,long
     * IdType.NONE 不生成主键值
     * IdType.INPUT 不生成主键值，需要在程序中自己填写
     * IdType.ASSIGN_ID 生成主键值，使用雪花算法，类型要求：long,String
     * IdType.ASSIGN_UUID 生成主键值，使用UUID，类型要求：string
     *
     * 推荐使用雪花算法：
     * 雪花算法：1.纯数字，可以排序 2.数据量小
     */
//    @TableId(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
//    private Long id;
    private String id;
    /**
     * 实体类中的属性与普通字段对应关系的描述
     * value对应字段名称
     * exist标志当前成员变量是否需要与表中的字段进行对应，默认值为true，表示存在，false表示不存在
     */
    @TableField(value = "name")
    private String userName;
    private Integer age;
    private String email;
    @TableField(exist = false)
    private String gender;
    /**
     * 逻辑删除
     * 1.需要在表中设计一个字段（删除标志） 0表示正常数据 1表示删除数据
     * 2.查询时必须携带删除标志的查询条件
     * 3.删除时仅需要更细删除标志字段的值为1即可
     * 逻辑删除字段
     * -- 物理删除
     * delete from tbl_user where id='98baf1737743f2d95b741c51eaf3c60c';
     * -- 逻辑删除
     * update tbl_user set del_flag=1 where id in ('1','2','3','4','5','6');
     * -- 查询列表
     * select * from tbl_user where del_flag=0;
     */
    @TableLogic
    private String delFlag;
}
