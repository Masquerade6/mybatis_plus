package com.mobei.mp.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.mobei.mp.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableField:
 *      value:
 *          对象中属性名和字段名不一致的问题(非驼峰)
 *              例如:
 *                  假设实体类中名字是mail,表中是email,这样就关联不上了,需要通过注解来指定:
 *                      @TableField(value = "email")
 *                      private String mail;
 *
 *      exist:
 *          对象中的属性字段在表中不存在的问题:
 *              假设实体类中有一个变量,表中没有对应的字段,那么执行的时候MP会把这个参数作为字段往表中添加,
 *              就会报错,因此需要告诉MP对象中的这个变量不是表中的字段:exist = false
 *              @TableField(exist = false)
 *              private String address;
 *
 *      select:
 *          不想把某个参数查询出来:
 *              比如不想把password查询出来:
 *                      @TableField(select = false)
 *                      private String password;
 *
 *      fill:配合MyMetaObjectHandler使用,设置自动填充的策略
 *          插入或者更新数据时,希望有些字段可以自动填充数据比如password
 *
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_user")
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String userName;

    @TableField(select = false, fill = FieldFill.INSERT)
    private String password;

    private String name;
    private Integer age;

//    private String email;
    @TableField(value = "email")
    private String mail;//这样和数据库中字段映射不上了,需要指定映射关系

    @TableField(exist = false)
    private String address;//在数据库表中是不存在address字段的

    /**
     * 表中需要有对应字段
     * 逻辑删除: 1-删除 0-未删除
     */
    @TableLogic
    private Integer deleted;

    /**
     * 性别:枚举类型  数据库中sex 类型为:int(1)
     */
    private SexEnum sex;

}
