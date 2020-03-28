package com.mobei.mp.injectors;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

import java.util.List;

public class MySqlInjector extends DefaultSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        //拿到父类的集合将我们自己写的方法添加后再返回,否则父类的方法全部不可用
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);

        methodList.add(new FindAll());
        return methodList;
    }
}
