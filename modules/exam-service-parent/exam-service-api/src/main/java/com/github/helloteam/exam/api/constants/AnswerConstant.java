package com.github.helloteam.exam.api.constants;

/**
 * 封装与答题相关的常量
 *
 * @author zdz
 * @date 2022/04/16 14:07
 */
public class AnswerConstant {

    /**
     * 正确
     */
    public static final Integer RIGHT = 0;

    /**
     * 错误
     */
    public static final Integer WRONG = 1;

    /**
     * 待批改
     */
    public static final Integer TO_BE_MARKED = 0;

    /**
     * 已经批改
     */
    public static final Integer MARKED = 1;

    /**
     * 当前题目
     */
    public static final Integer CURRENT = -1;

    /**
     * 下一题
     */
    public static final Integer NEXT = 0;

    /**
     * 上一题
     */
    public static final Integer PREVIOUS = 1;

	/**
	 * 排名缓存前缀
	 */
	public static final String CACHE_PREFIX_RANK = "rank:";

}