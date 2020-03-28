package com.mobei.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mobei.mp.pojo.User;

public interface UserMapper extends BaseMapper<User> {
    User findById(Long id);
}
