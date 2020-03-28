package com.mobei.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mobei.mp.pojo.User2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * ActiveRecord也属于ORM（对象关系映射）层，由Rails最早提出，遵循标准的ORM模型：表映射到记录，记
 * 录映射到对象，字段映射到对象属性。配合遵循的命名和配置惯例，能够很大程度的快速实现模型的操作，而
 * 且简洁易懂。
 * ActiveRecord的主要思想是：
 *      每一个数据库表对应创建一个类，类的每一个对象实例对应于数据库中表的一行记录；通常表的每个字段
 *          在类中都有相应的Field；
 *      ActiveRecord同时负责把自己持久化，在ActiveRecord中封装了对数据库的访问，即CURD;；
 *      ActiveRecord是一种领域模型(Domain Model)，封装了部分业务逻辑；
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MyBatisMpSb2 {
    //不需要注入UserMapper即可操作,但是意味着可以删除或者不写UserMapper,因为Model中还是需要使用我们写的UserMapper去执行响应的方法的

    @Test
    public void testSelectById() {
        User2 user2 = new User2();
        user2.setId(2L);
        User2 user = user2.selectById();
        System.out.println(user);
    }

    @Test
    public void testInsert() {
        User2 user2 = new User2();
        user2.setUserName("mobei");
        user2.setPassword("123456");
        user2.setAge(30);
        user2.setName("模杯");
        user2.setMail("mobei@mobei.com");

        //调用AR的insert
        boolean res = user2.insert();
        System.out.println("result : " + res);
    }

    @Test
    public void testUpdate() {
        User2 user2 = new User2();
        user2.setUserName("juhua");
        user2.setPassword("123456");
        user2.setAge(31);
        user2.setName("巨化");
        user2.setMail("mobei@mobei.com");
        user2.setId(2L);

//        boolean res = user2.insertOrUpdate();

        //根据主键更新
        boolean res = user2.updateById();

//        QueryWrapper wrapper = new QueryWrapper();
//        user2.update(wrapper);

        System.out.println("result : " + res);
    }

    /**
     * 测试全表更新 SQL分析器的阻断效果
     */
    @Test
    public void testUpdateAll() {
        User2 user2 = new User2();
        boolean res = user2.update(null);

        System.out.println("result : " + res);
    }

    @Test
    public void testDelete() {
        User2 user2 = new User2();

        boolean b = user2.deleteById(10L);

//        user2.setId(10L);
        //如果不设置id则会报错
//        boolean b = user2.deleteById();

//        QueryWrapper wrapper = new QueryWrapper();
//        user2.delete(wrapper);

        System.out.println("result : " + b);
    }

    @Test
    public void testSelect() {
        User2 user2 = new User2();

        QueryWrapper<User2> wrapper = new QueryWrapper<>();
        wrapper.ge("age", 30);

        List<User2> user2List = user2.selectList(wrapper);

        System.out.println("result : " + user2List);
    }

}
