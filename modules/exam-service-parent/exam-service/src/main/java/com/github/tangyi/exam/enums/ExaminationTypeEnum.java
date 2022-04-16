package com.github.tangyi.exam.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 考试类型枚举类
 *
 * @author zdz
 * @date 2022/04/16 14:22
 */
@Getter
@AllArgsConstructor
public enum ExaminationTypeEnum {

    /**
     * 正式考试
     */
    FORMAL("正式考试", 0),

    /**
     * 模拟考试
     */
    MOCK("模拟考试", 1),

    /**
     * 练习测试
     */
    PRACTICE("练习", 2),

    /**
     * 调查问卷
     */
    QUESTIONNAIRE("调查问卷", 3);;

    /**
     * 字符串值
     */
    private String name;

    /**
     * int值
     */
    private Integer value;

    /**
     * 根据类型返回具体的ExaminationTypeEnum（默认模拟考试）
     *
     * @param value value
     * @return ExaminationTypeEnum
     */
    public static ExaminationTypeEnum matchByValue(Integer value) {
        for (ExaminationTypeEnum item : ExaminationTypeEnum.values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        return MOCK;
    }

    /**
     * 根据描述返回具体的ExaminationTypeEnum（默认模拟考试）
     *
     * @param name name
     * @return ExaminationTypeEnum
     */
    public static ExaminationTypeEnum matchByName(String name) {
        for (ExaminationTypeEnum item : ExaminationTypeEnum.values()) {
            if (item.name.equals(name)) {
                return item;
            }
        }
        return MOCK;
    }

}
