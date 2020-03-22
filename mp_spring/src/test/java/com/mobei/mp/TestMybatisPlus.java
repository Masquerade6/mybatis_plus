package com.mobei.mp;

import com.mobei.mp.mapper.UserMapper;
import com.mobei.mp.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestMybatisPlus {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testFindAll() throws IOException {
        List<User> userList = userMapper.selectList(null);
        System.out.println(userList);
    }

}
