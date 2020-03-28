package com.mobei.mp;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import com.mobei.mp.injectors.MySqlInjector;
import com.mobei.mp.plugins.MyInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@MapperScan("com.mobei.mp.mapper")
public class MyBatisPlusConfig {

    /**
     * 配置分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 注入自定义的拦截器
     * @return
     */
    @Bean
    public MyInterceptor myInterceptor() {
        return new MyInterceptor();
    }

    /**
     * 在MP中提供了对SQL执行的分析的插件，可用作阻断全表更新、删除的操作，注意：该插件仅适用于开发环境，不
     * 适用于生产环境。
     * @return
     */
    @Bean
    public SqlExplainInterceptor sqlExplainInterceptor() {
        SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();
        List<ISqlParser>  sqlParserList = new ArrayList<>();
        //攻击 SQL 阻断解析器、加入解析链
        sqlParserList.add(new BlockAttackSqlParser());
        sqlExplainInterceptor.setSqlParserList(sqlParserList);
        return sqlExplainInterceptor;
    }

//    @Bean
//    public PerformanceInterceptor performanceInterceptor() {
//        PerformanceMonitorInterceptor interceptor = new PerformanceMonitorInterceptor();
//        com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor
//    }

    /**
     * 意图：
     *      当要更新一条记录的时候，希望这条记录没有被别人更新
     * 乐观锁实现方式：
     *      取出记录时，获取当前version
     *      更新时，带上这个version
     *      执行更新时， set version = newVersion where version = oldVersion
     *      如果version不对，就更新失败
     *
     * 注意:表中需要有对应的version字段,并且需要在实体类上使用@Version标注对应的字段
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    @Bean
    public MySqlInjector mySqlInjector() {
        return new MySqlInjector();
    }

}
