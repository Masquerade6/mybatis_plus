package com.mobei.mp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ActiveRecord也属于ORM（对象关系映射）层，由Rails最早提出，遵循标准的ORM模型：表映射到记录，记
 * 录映射到对象，字段映射到对象属性。配合遵循的命名和配置惯例，能够很大程度的快速实现模型的操作，而
 * 且简洁易懂。
 * ActiveRecord的主要思想是：
 *      每一个数据库表对应创建一个类，类的每一个对象实例对应于数据库中表的一行记录；通常表的每个字段
 *          在类中都有相应的Field；
 *      ActiveRecord同时负责把自己持久化，在ActiveRecord中封装了对数据库的访问，即CURD;；
 *      ActiveRecord是一种领域模型(Domain Model)，封装了部分业务逻辑；
 *
 * ActiveRecord开启只需要实体类继承Model类即可
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_user")
public class User2 extends Model<User2> {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String userName;

    @TableField(select = false)
    private String password;

    private String name;
    private Integer age;

//    private String email;
    @TableField(value = "email")
    private String mail;//这样和数据库中字段映射不上了,需要指定映射关系

    @TableField(exist = false)
    private String address;//在数据库表中是不存在address字段的

}
