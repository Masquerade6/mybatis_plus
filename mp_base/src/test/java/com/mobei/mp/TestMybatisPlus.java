package com.mobei.mp;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.mobei.mp.mapper.UserMapper;
import com.mobei.mp.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatisPlus {

    @Test
    public void testFindAll() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //通过MybatisSqlSessionFactoryBuilder构建之后就完成了整合
        SqlSessionFactory sqlSessionFactory = new MybatisSqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//        List<User> userList = userMapper.findAll();
        List<User> userList = userMapper.selectList(null);
        System.out.println(userList);
    }

}
