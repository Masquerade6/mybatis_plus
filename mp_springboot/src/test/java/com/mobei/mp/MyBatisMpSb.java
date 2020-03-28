package com.mobei.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mobei.mp.enums.SexEnum;
import com.mobei.mp.mapper.UserMapper;
import com.mobei.mp.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
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
        user.setSex(SexEnum.MAN);

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

    /**
     * 根据id查询
     */
    @Test
    public void testSelectById() {
        User user = userMapper.selectById(2L);
        System.out.println(user);
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

    /**
     * 根据id批量删除
     */
    @Test
    public void testDeleteBatchIds() {
        int res = userMapper.deleteBatchIds(Arrays.asList(8L, 9L));
        System.out.println("根据id批量删除: " + res);
    }

    /**
     * 根据id批量查询
     */
    @Test
    public void testSelectBatchIds() {
        List<User> userList = userMapper.selectBatchIds(Arrays.asList(1L, 2L));
        System.out.println("根据id批量查询: " + userList);
    }

    /**
     * 根据entity条件查询一条记录
     */
    @Test
    public void testSelectOne() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //查询条件
        wrapper.eq("user_name", "lisi");
        //如果不存在则返回user=null,如果返回结果有多条会报错
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    /**
     * 根据wrapper条件查询总记录数
     */
    @Test
    public void testSelectCount() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //查询条件:年龄大于20岁
        wrapper.gt("age", "20");
        //如果不存在则返回user=null,如果返回结果有多条会报错
        int count = userMapper.selectCount(wrapper);
        System.out.println(count);
    }

    /**
     * 根据entity条件查询全部记录
     */
    @Test
    public void testSelectList2() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //查询条件:年龄大于20岁
        wrapper.like("email", "mobei");
        //如果不存在则返回user=null,如果返回结果有多条会报错
        List<User> userList = userMapper.selectList(wrapper);
        System.out.println(userList);
    }

    /**
     * 分页查询
     */
    @Test
    public void testSelectPage() {
        // 查询第一页第一条
        Page<User> page = new Page<>(1, 2);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //查询条件:年龄大于20岁
        wrapper.like("email", "mobei");

        IPage<User> userPage = userMapper.selectPage(page, wrapper);

        System.out.println(userPage.getRecords());
    }

    /**
     * 自定义方法
     */
    @Test
    public void testFindById() {
        User user = userMapper.findById(2L);
        System.out.println(user);
    }

    @Test
    public void testAllEq() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        Map<String, Object> params = new HashMap<>();
        params.put("name", "李四");
        params.put("age", "20");
        params.put("password", null);

        //SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE (password IS NULL AND name = ? AND age = ?)
//        wrapper.allEq(params);

        //false表示空值不作为条件
        //SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE (name = ? AND age = ?)
//        wrapper.allEq(params, false);

        //传入的参数能否作为查询条件取决于前面比较的结果:
        //比如这里,如果map的key不是age或者id就不会作为查询参数:只有age=20作为查询参数
        //SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE (age = ?)
        wrapper.allEq((k, v) -> (k.equals("age") || k.equals("id")), params);

        List<User> userList = userMapper.selectList(wrapper);
        System.out.println(userList);
    }

    @Test
    public void testEq() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        //SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE (password = ? AND age >= ? AND name IN (?,?))
        wrapper
                .eq("password", "123456")
                .ge("age", 20)
                .in("name", "李四", "王五");

        List<User> userList = userMapper.selectList(wrapper);
        System.out.println(userList);
    }

    @Test
    public void testLike() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        /**
         * SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE (name LIKE ?)
         * %五(String)
         */
        wrapper.likeLeft("name", "五");

        List<User> userList = userMapper.selectList(wrapper);
        System.out.println(userList);
    }

    @Test
    public void testOr() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //姓名为王五或者年龄是21岁的
        wrapper
                .eq("name", "王五")
                .or()
                .eq("age", 21);

        List<User> userList = userMapper.selectList(wrapper);
        System.out.println(userList);
    }

    /**
     * 指定查询的字段
     */
    @Test
    public void testSelect() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //姓名为王五或者年龄是21岁的
        wrapper
                .eq("name", "王五")
                .or()
                .eq("age", 21)
                .select("id", "name", "age");

        List<User> userList = userMapper.selectList(wrapper);
        System.out.println(userList);
    }
}
