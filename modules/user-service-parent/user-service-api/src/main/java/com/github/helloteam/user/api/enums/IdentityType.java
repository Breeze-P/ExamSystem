package com.github.helloteam.user.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户授权类型枚举类
 *
 * @author zdz
 * @date 2022/04/15 00:10
 */
@Getter
@AllArgsConstructor
public enum IdentityType {

	/**
	 * 密码
	 */
	PASSWORD(1, "密码"),

	/**
	 * 手机号
	 */
	PHONE_NUMBER(2, "手机号"),

	/**
	 * 邮箱
	 */
	EMAIL(3, "邮箱"),

	/**
	 * 微信
	 */
	WE_CHAT(4, "微信"),

	/**
	 * QQ
	 */
	QQ(5, "QQ");

	/**
	 * int值
	 */
	private Integer value;

	/**
	 * 字符串描述
	 */
	private String desc;

	/**
	 * 根据类型返回具体的IdentityType，默认密码
	 *
	 * @param type int值
	 * @return 具体的IdentityType
	 */
	public static IdentityType matchByType(Integer type) {
		for (IdentityType item : IdentityType.values()) {
			if (item.value.equals(type)) {
				return item;
			}
		}
		return IdentityType.PASSWORD;
	}

	/**
	 * 根据描述返回具体的IdentityType，默认密码
	 *
	 * @param desc 字符串描述
	 * @return 具体的IdentityType
	 */
	public static IdentityType matchByDesc(String desc) {
		for (IdentityType item : IdentityType.values()) {
			if (item.desc.equals(desc)) {
				return item;
			}
		}
		return IdentityType.PASSWORD;
	}

}
