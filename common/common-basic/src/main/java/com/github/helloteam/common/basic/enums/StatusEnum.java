package com.github.helloteam.common.basic.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态枚举类
 *
 * @author zdz
 * @date 2022/04/10 14:43
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {

    /**
     * 状态启用
     */
    ENABLE("启用", 0),

    /**
     * 状态禁用
     */
    DISABLE("禁用", 1);

    /**
     * 状态string值
     */
    private String name;

    /**
     * 状态int值
     */
    private Integer value;

    /**
     * 根据给定状态int值匹配当前状态
     *
     * @param value 状态值
     * @return 当前状态
     */
    public static StatusEnum matchByValue(Integer value) {
        for (StatusEnum item : StatusEnum.values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        return ENABLE;
    }

    /**
     * 根据给定状态string值匹配当前状态
     *
     * @param name 状态字符串
     * @return 当前状态
     */
    public static StatusEnum matchByName(String name) {
        for (StatusEnum item : StatusEnum.values()) {
            if (item.name.equals(name)) {
                return item;
            }
        }
        return ENABLE;
    }

}
