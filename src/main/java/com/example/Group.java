package com.example;

import lombok.Data;
import lombok.Getter;

@Getter
public enum Group {
    GOOD("GOOD","红方"),
    BLUE("BLUE","蓝方"),
    ;


    Group(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;
    private String desc;

    /**
     * 根据Code获取Desc
     */
    public static String getDescByCode(String code) {
        for (Group item : Group.values()) {
            if (item.getCode().equals(code)) {
                return item.getDesc();
            }
        }
        return null;
    }
}
