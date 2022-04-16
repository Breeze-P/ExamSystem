package com.github.helloteam.exam.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 提交状态
 *
 * @author zdz
 * @date 2022/04/16 14:25
 */
@Getter
@AllArgsConstructor
public enum SubmitStatusEnum {

	/**
	 * 已提交
	 */
	SUBMITTED("已提交", 0),

	/**
	 * 未提交
	 */
	UNSUBMITTED("未提交", 1);

	/**
	 * 字符串值
	 */
	private String name;

	/**
	 * int值
	 */
	private Integer value;

	public static SubmitStatusEnum matchByValue(Integer value) {
		for (SubmitStatusEnum item : SubmitStatusEnum.values()) {
			if (item.value.equals(value)) {
				return item;
			}
		}
		return UNSUBMITTED;
	}

	public static SubmitStatusEnum matchByName(String name) {
		for (SubmitStatusEnum item : SubmitStatusEnum.values()) {
			if (item.name.equals(name)) {
				return item;
			}
		}
		return UNSUBMITTED;
	}

}
