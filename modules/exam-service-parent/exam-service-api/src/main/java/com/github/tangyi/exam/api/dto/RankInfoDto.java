package com.github.tangyi.exam.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 排名详情
 *
 * @author zdz
 * @date 2022/04/16 14:17
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RankInfoDto {

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 头像地址
	 */
	private String avatarUrl;

	/**
	 * 分数
	 */
	private Double score;

	/**
	 * 排名
	 */
	private Integer rankNum;

}
