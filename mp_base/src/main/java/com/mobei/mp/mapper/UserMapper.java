package com.mobei.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mobei.mp.pojo.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<User> findAll();
}
