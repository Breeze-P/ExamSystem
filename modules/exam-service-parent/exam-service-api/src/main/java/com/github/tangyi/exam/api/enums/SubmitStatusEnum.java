package com.github.tangyi.exam.api.enums;

/**
 * 提交状态枚举类
 *
 * @author zdz
 * @date 2022/04/16 14:07
 */
public enum SubmitStatusEnum {

    /**
     * 未提交
     */
    NOT_SUBMITTED("未提交", 0),

    /**
     * 已提交
     */
    SUBMITTED("已提交", 1),

    /**
     * 正在统计中
     */
    CALCULATE("正在统计", 2),

    /**
     * 统计完成
     */
    CALCULATED("统计完成", 3);

    /**
     * 字符串值
     */
    private String name;

    /**
     * int值
     */
    private Integer value;

    SubmitStatusEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * 根据所给的int值和枚举属性匹配对应的提交状态
     *
     * @param value        int值
     * @param defaultValue 枚举属性
     * @return 提交状态
     */
    public static SubmitStatusEnum match(Integer value, SubmitStatusEnum defaultValue) {
        if (value != null) {
            for (SubmitStatusEnum item : SubmitStatusEnum.values()) {
                if (item.value.equals(value)) {
                    return item;
                }
            }
        }
        return defaultValue;
    }

}
