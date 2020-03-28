package com.mobei.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * MyBatis-Plus中的BaseMapper是没有提供该方法的,
 * 我们想要注入该方法使得每次使用的时候都有这个方法,
 * 后续我们也就不再继承BaseMapper而是继承MyBaseMapper,
 * 并且不要每次都在xml中去写对应的SQL
 * @return
 */
public interface MyBaseMapper<T> extends BaseMapper<T> {
    List<T> findAll();
}
