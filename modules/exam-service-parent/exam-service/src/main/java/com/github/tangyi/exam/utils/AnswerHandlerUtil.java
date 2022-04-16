package com.github.tangyi.exam.utils;

import com.github.tangyi.exam.handler.AnswerHandleResult;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * 处理答案的工具类
 *
 * @author zdz
 * @date 2022/04/16 14:31
 */
public class AnswerHandlerUtil {

	/**
	 * 正则表达式相关
	 */
	private static final String REGEX_COMMA = "^,*|,*$";

	/**
	 * 记录答案，正确的与错误的
	 * @param results 答案
	 * @return 答案
	 */
	public static AnswerHandleResult addAll(List<AnswerHandleResult> results) {
		AnswerHandleResult result = new AnswerHandleResult();
		int score = 0;
		int correctNum = 0;
		int inCorrectNum = 0;
		for (AnswerHandleResult tempResult : results) {
			if (tempResult != null) {
				score += tempResult.getScore();
				correctNum += tempResult.getCorrectNum();
				inCorrectNum += tempResult.getInCorrectNum();
			}
		}
		result.setScore(score);
		result.setCorrectNum(correctNum);
		result.setInCorrectNum(inCorrectNum);
		return result;
	}

	/**
	 * 替换收尾的逗号
	 * @param str str
	 * @return String
	 */
	public static String replaceComma(String str) {
		if (StringUtils.isNotBlank(str)) {
			str = str.replaceAll(REGEX_COMMA, "");
		}
		return str;
	}

}
