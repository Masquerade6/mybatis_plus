package com.mobei.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mobei.mp.mapper.UserMapper;
import com.mobei.mp.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MyBatisMpSb {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        List<User> userList = userMapper.selectList(null);
        System.out.println(userList);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setName("关羽");
        user.setMail("guanyu@mobei.com");
        user.setAge(30);
        user.setPassword("123456");
        user.setUserName("guanyu");

        //表中如果没有address字段,但是对象中定义了,如果不处理就会报错
        //如果想要正常执行需要在对象中
        user.setAddress("长沙");

        //影响行数
        int result = userMapper.insert(user);

        System.out.println("row effected:   " + result);

        /**
         * 如果对象id生成策略不指定:@TableId(type = IdType.AUTO),那么这里的id在数据库中就不是自增长的了
         * 例如:
         *  不指定:1241701785849036801
         *  指定后(5):6
         */
        System.out.println(user.getId());
    }

    @Test
    public void testUpdateById() {
        User user = new User();
        user.setId(1L);
        user.setAge(19);
        user.setPassword("666666");

        int res = userMapper.updateById(user);
        System.out.println(res);
    }

    /**
     * 根据zhangsan来更新
     */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setAge(20);
        user.setPassword("888888");

        QueryWrapper<User> wrapper = new QueryWrapper();

        //注意:第1个参数是表中的字段名,不是实体类中的字段名
        wrapper.eq("user_name", "zhangsan");
        int res = userMapper.update(user, wrapper);
        System.out.println(res);
    }

    /**
     * 根据zhangsan来更新
     */
    @Test
    public void testUpdate2() {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("age", 21).set("password", "999999")//设置更新的字段
                .eq("user_name", "zhangsan")//更新的条件
        ;
        int res = userMapper.update(null, wrapper);
        System.out.println(res);
    }

    @Test
    public void testDeleteById() {
        int res = userMapper.deleteById(9L);
        System.out.println(res);
    }

    @Test
    public void testDeleteByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", "zhangsan");
        map.put("password", "123456");

        //根据map删除数据,多条件之间是and关系
        int res = userMapper.deleteByMap(map);
        System.out.println(res);
    }

    @Test
    public void testDelete() {
        //方式一:
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("user_name", "caocao")
//        .eq("password", "123456");

        //方式二
        User user = new User();
        user.setAge(20);
        user.setPassword("888888");
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);

        //根据map删除数据,多条件之间是and关系
        int res = userMapper.delete(wrapper);
        System.out.println(res);
    }



}
