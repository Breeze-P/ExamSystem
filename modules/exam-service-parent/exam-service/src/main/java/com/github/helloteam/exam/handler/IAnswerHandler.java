package com.github.helloteam.exam.handler;

import com.github.helloteam.exam.api.dto.SubjectDto;
import com.github.helloteam.exam.api.module.Answer;
import com.github.helloteam.exam.enums.SubjectTypeEnum;

import java.util.List;

/**
 * 统计成绩处理器
 *
 * @author zdz
 * @date 2022/04/16 14:47
 */
public interface IAnswerHandler {

    /**
     * 正确
     */
    String TRUE = Boolean.TRUE.toString();

    /**
     * 错误
     */
    String FALSE = Boolean.FALSE.toString();

    /**
     * 统计成绩
     *
     * @param answers answers
     * @return HandleResult
     */
    AnswerHandleResult handle(List<Answer> answers);

    /**
     * 获取题目类型
     *
     * @return SubjectTypeEnum
     */
    SubjectTypeEnum getSubjectType();

    /**
     * 获取题目列表
     *
     * @param answers answers
     * @return List
     */
    List<SubjectDto> getSubjects(List<Answer> answers);

    /**
     * 判断逻辑
     *
     * @param answer     answer
     * @param subject    subject
     * @param rightScore rightScore
     */
    void judge(Answer answer, SubjectDto subject, List<Double> rightScore);

    /**
     * 判断答题是否正确
     *
     * @param answer  answer
     * @param subject subject
     */
    boolean judgeRight(Answer answer, SubjectDto subject);

}
