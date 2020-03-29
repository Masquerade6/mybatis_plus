package com.mobei.mp.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mobei.mp.user.entity.TbUser;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mobei
 * @since 2020-03-29
 */
public interface TbUserMapper extends BaseMapper<TbUser> {
    List<TbUser> findAll();
}
