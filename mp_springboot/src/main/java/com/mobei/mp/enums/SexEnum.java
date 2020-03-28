package com.mobei.mp.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

public enum  SexEnum implements IEnum<Integer> {
    //男
    MAN(1, "男"),
    //女
    WOMAN(2, "女");

    private int value;
    private String desc;

    SexEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "SexEnum{" +
                "value=" + value +
                ", desc='" + desc + '\'' +
                '}';
    }
}
