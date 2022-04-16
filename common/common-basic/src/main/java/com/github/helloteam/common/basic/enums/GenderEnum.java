package com.github.helloteam.common.basic.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别枚举类
 *
 * @author zdz
 * @date 2022/04/10 14:32
 */
@Getter
@AllArgsConstructor
public enum GenderEnum {

    /**
     * 性别男
     */
    MEN("男", 0),

    /**
     * 性别女
     */
    WOMEN("女", 1);

    /**
     * 性别字符串
     */
    private String name;

    /**
     * 性别值
     */
    private Integer value;

    /**
     * 根据所给的int值匹配性别
     *
     * @param value 性别值
     * @return 性别
     */
    public static GenderEnum matchByValue(Integer value) {
        for (GenderEnum item : GenderEnum.values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        return MEN;
    }

    /**
     * 根据所给的string值匹配性别
     *
     * @param name 性别名称
     * @return 性别
     */
    public static GenderEnum matchByName(String name) {
        for (GenderEnum item : GenderEnum.values()) {
            if (item.name.equals(name)) {
                return item;
            }
        }
        return MEN;
    }

}
