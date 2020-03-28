package com.mobei.mp.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 添加数据的时候填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //先获取到password的值进行判断,如果为空就填充,否则不处理
        Object password = getFieldValByName("password", metaObject);
        if (password == null) {
            setFieldValByName("password", "888888", metaObject);
        }
    }

    /**
     * 更新数据的时候填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
