package com.github.helloteam.exam.enums;

import com.github.helloteam.exam.service.ISubjectService;
import com.github.helloteam.exam.service.SubjectChoicesService;
import com.github.helloteam.exam.service.SubjectJudgementService;
import com.github.helloteam.exam.service.SubjectShortAnswerService;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 题目类型枚举
 *
 * @author zdz
 * @date 2022/04/16 14:24
 */
@Getter
@AllArgsConstructor
public enum SubjectTypeEnum {

	/**
	 * 选择题
	 */
	CHOICES("选择题", 0, SubjectChoicesService.class),

	/**
	 * 简答题
	 */
	SHORT_ANSWER("简答题", 1, SubjectShortAnswerService.class),

	/**
	 * 判断题
	 */
	JUDGEMENT("判断题", 2, SubjectJudgementService.class),

	/**
	 * 多选题
	 */
	MULTIPLE_CHOICES("多选题", 3, SubjectChoicesService.class);

	/**
	 * 字符串值
	 */
	private String name;

	/**
	 * int值
	 */
	private Integer value;

	private Class<? extends ISubjectService> service;

	/**
	 * 根据类型返回具体的SubjectType
	 *
	 * @param value value
	 * @return SubjectType
	 */
	public static SubjectTypeEnum matchByValue(Integer value) {
		for (SubjectTypeEnum item : SubjectTypeEnum.values()) {
			if (item.value.equals(value)) {
				return item;
			}
		}
		return CHOICES;
	}

	/**
	 * 根据描述返回具体的SubjectType
	 *
	 * @param name name
	 * @return SubjectType
	 */
	public static SubjectTypeEnum matchByName(String name) {
		for (SubjectTypeEnum item : SubjectTypeEnum.values()) {
			if (item.name.equals(name)) {
				return item;
			}
		}
		return CHOICES;
	}

}
