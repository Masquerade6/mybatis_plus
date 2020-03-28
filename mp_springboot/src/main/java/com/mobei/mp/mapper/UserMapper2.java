package com.mobei.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mobei.mp.pojo.User2;

public interface UserMapper2 extends BaseMapper<User2> {
    User2 findById(Long id);
}
