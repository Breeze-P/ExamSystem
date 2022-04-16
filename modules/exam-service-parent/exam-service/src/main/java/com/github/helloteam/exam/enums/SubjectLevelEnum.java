package com.github.helloteam.exam.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 题目难度级别
 *
 * @author zdz
 * @date 2022/04/16 14:23
 */
@Getter
@AllArgsConstructor
public enum SubjectLevelEnum {

	/**
	 * 简单
	 */
	SIMPLE("简单", 0),

	/**
	 * 一般
	 */
	NORMAL("一般", 1),

	/**
	 * 略难
	 */
	DIFFICULT("略难", 2),

	/**
	 * 非常难
	 */
	MORE_DIFFICULT("非常难", 3);

	/**
	 * 字符串值
	 */
	private String name;

	/**
	 * int值
	 */
	private Integer value;

	/**
	 * 根据类型返回具体的SubjectLevel（默认一般难度）
	 *
	 * @param value value
	 * @return SubjectLevelEnum
	 */
	public static SubjectLevelEnum matchByValue(Integer value) {
		for (SubjectLevelEnum item : SubjectLevelEnum.values()) {
			if (item.value.equals(value)) {
				return item;
			}
		}
		return NORMAL;
	}

	/**
	 * 根据描述返回具体的SubjectLevel（默认一般难度）
	 *
	 * @param name name
	 * @return SubjectLevelEnum
	 */
	public static SubjectLevelEnum matchByName(String name) {
		for (SubjectLevelEnum item : SubjectLevelEnum.values()) {
			if (item.name.equals(name)) {
				return item;
			}
		}
		return NORMAL;
	}

}
